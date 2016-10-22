
import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.*;
 
public class SpiderChart_K extends JPanel {
	
	static float score_TheethNum = 0;
	static float score_DentalCaries = 0;
	static float score_PeriodontalStatus = 0;
	static float score_OtherMouthStatus = 0;
	static float score_MouthHabit = 0;
    
        public SpiderChart_K(float s1, float s2, float s3, float s4, float s5) {
        	
        	score_TheethNum = s1;
        	score_DentalCaries = s2;
        	score_PeriodontalStatus = s3;
        	score_OtherMouthStatus = s4;
        	score_MouthHabit = s5;
        	
                JPanel jpanel = createDemoPanel();
                jpanel.setPreferredSize(new Dimension(700, 500));
                this.add(jpanel);
        }
 
        private static CategoryDataset createDataset() {
            String graph = "획득점수";
            String element_TheethNum = "치아수";
            String element_DentalCaries = "치아우식";
            String element_PeriodontalStatus = "치주상태";
            String element_OtherMouthStatus = "기타구강상태";
            String element_MouthHabit = "구강관리습관";
 
                DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
                defaultcategorydataset.addValue(score_TheethNum, graph, element_TheethNum);
                defaultcategorydataset.addValue(score_DentalCaries, graph, element_DentalCaries);
                defaultcategorydataset.addValue(score_PeriodontalStatus, graph, element_PeriodontalStatus);
                defaultcategorydataset.addValue(score_OtherMouthStatus, graph, element_OtherMouthStatus);
                defaultcategorydataset.addValue(score_MouthHabit, graph, element_MouthHabit);
                return defaultcategorydataset;
        }
 

 
        private static JFreeChart createChart(CategoryDataset categorydataset) {
 
                SpiderWebPlot spiderwebplot = new SpiderWebPlot(categorydataset);
                spiderwebplot.setStartAngle(54D);
                spiderwebplot.setInteriorGap(0.40000000000000002D);
                spiderwebplot.setToolTipGenerator(new StandardCategoryToolTipGenerator());
                JFreeChart jfreechart = new JFreeChart("환자 구강상태 결과", TextTitle.DEFAULT_FONT, spiderwebplot, false);
                LegendTitle legendtitle = new LegendTitle(spiderwebplot);
                legendtitle.setPosition(RectangleEdge.BOTTOM);
                jfreechart.addSubtitle(legendtitle);
                return jfreechart;
        }

 
        public static JPanel createDemoPanel() {
                JFreeChart jfreechart = createChart(createDataset());
                return new ChartPanel(jfreechart);
        }
        
        /*
 
        public static void main(String args[]) {
                SpiderChart spiderwebchartdemo1 = new SpiderChart("SpiderWebChartDemo1");
                //spiderwebchartdemo1.pack();
                //RefineryUtilities.centerFrameOnScreen(spiderwebchartdemo1);
                spiderwebchartdemo1.setVisible(true);
        }
        */
}
 

