package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Food implements Serializable  {

   private int id;
   private String name;
   private String explain;
   private String price;
   private ArrayList<Material> marterialList;

//   public Food() {
//      this(0,"","",new ArrayList<Integer>());
//   }

//   public Food(int id, String name, String explain, String price, List <Integer> list) {
//      this.id=id;
//      this.name=name;
//      this.explain=explain;
//      this.price=price;
//      this.merterialList=list;
//      
//   }

//   public void add(int id){
//      merterialList.add(id);
//   }
//   public void remove(int id){
//      merterialList.remove(id);
//   }
//   public void update(int preid,int postid){
//      int i=merterialList.indexOf(preid);
//      merterialList.set(i, postid);
//   }
   public int getId() {
      return id;
   }
   public void setId(int id) {
      this.id = id;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getExplain() {
      return explain;
   }
   public void setExplain(String explain) {
      this.explain = explain;
   }
   public String getPrice() {
      return price;
   }
   public void setPrice(String price) {
      this.price = price;
   }
   public ArrayList<Material> getMaterialList() {
      return marterialList;
   }
   public void setMaterialList(ArrayList<Material> list) {
      this.marterialList = list;
   }
   
}