
import java.awt.*;  
import java.awt.event.*;  
import java.awt.geom.*;  
import java.awt.image.BufferedImage;  
import java.io.*;  
import java.net.*;  
import javax.imageio.ImageIO;  
import javax.swing.*;  
import javax.swing.event.*;  
   
public class EnlargePicture_K   
{  
	
	EnlargePicture_K(String filePath){
		ImagePanel panel = new ImagePanel(filePath);  
        ImageZoom zoom = new ImageZoom(panel);  
        JFrame f = new JFrame();  
        f.getContentPane().add(zoom.getUIPanel(), "North");  
        f.getContentPane().add(new JScrollPane(panel)); 
        //panel.addMouseWheelListener(new ScaleMotionListener());
        f.setSize(500,400);  
        f.setLocation(200,200);  
        f.setVisible(true); 
	}
}  
   
class ImagePanel extends JPanel  
{  
    BufferedImage image;  
    double scale;  
    String path = "";
   
    public ImagePanel(String filePath)  
    {  
    	path = filePath;
        loadImage();  
        scale = 1.0;  
        setBackground(Color.black);  
        
    }  
   
    protected void paintComponent(Graphics g)  
    {  
        super.paintComponent(g);  
        Graphics2D g2 = (Graphics2D)g;  
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,  
                            RenderingHints.VALUE_INTERPOLATION_BICUBIC);  
        int w = getWidth();  
        int h = getHeight();  
        int imageWidth = image.getWidth();  
        int imageHeight = image.getHeight();  
        double x = (w - scale * imageWidth)/2;  
        double y = (h - scale * imageHeight)/2;  
        AffineTransform at = AffineTransform.getTranslateInstance(x,y);  
        at.scale(scale, scale);  
        g2.drawRenderedImage(image, at);  
    }  
   
    /** 
     * For the scroll pane. 
     */  
    public Dimension getPreferredSize()  
    {  
        int w = (int)(scale * image.getWidth());  
        int h = (int)(scale * image.getHeight());  
        return new Dimension(w, h);  
    }  
   
    public void setScale(double s)  
    {  
        scale = s;  
        revalidate();      // update the scroll pane  
        repaint();  
    }  
   
    private void loadImage()  
    {  
        String fileName = path;  
        
        try  
        {  
        	URL url = new File(fileName).toURI().toURL(); 
            System.out.println(url);
            image = ImageIO.read(url);  
            //image =  ((image).getImage()).getScaledInstance(150, 100, java.awt.Image.SCALE_SMOOTH);
        }  
        catch(MalformedURLException mue)  
        {  
            System.out.println("URL trouble: " + mue.getMessage());  
        }  
        catch(IOException ioe)  
        {  
            System.out.println("read trouble: " + ioe.getMessage());  
        }  
        
    }  
}  
   
class ImageZoom  
{  
    ImagePanel imagePanel;  
    float scale = (float)1.0;
    
   
    public ImageZoom(ImagePanel ip)  
    {  
        imagePanel = ip; 
        imagePanel.addMouseWheelListener(new MouseWheelListener() {
        	public void mouseWheelMoved(MouseWheelEvent e){
        		int steps = e.getWheelRotation();
        		if(steps<0){
        			scale = (float) (scale+0.01);
        			imagePanel.setScale(scale); 
        		}
        		else if(steps>0){
        			scale = (float) (scale-0.01);
        			imagePanel.setScale(scale);
        		}
        	}
        });
    }  
   
    public JPanel getUIPanel()  
    {  
        SpinnerNumberModel model = new SpinnerNumberModel(1.0, 0.1, 1.4, .01);  
        final JSpinner spinner = new JSpinner(model);  
        spinner.setPreferredSize(new Dimension(45, spinner.getPreferredSize().height));  
        spinner.addChangeListener(new ChangeListener()  
        {  
            public void stateChanged(ChangeEvent e)  
            {  
                //float scale = ((Double)spinner.getValue()).floatValue(); 
            	scale = ((Double)spinner.getValue()).floatValue();
                imagePanel.setScale(scale);  
            }  
        });  
        JPanel panel = new JPanel();  
        //panel.add(new JLabel("scale"));  
        //panel.add(spinner);  
        return panel;  
    }  
}  
