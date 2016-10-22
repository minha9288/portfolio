//package Recommand;
//
//import org.apache.mahout.cf.taste.impl.model.file.*;
//import org.apache.mahout.cf.taste.impl.neighborhood.*;
//import org.apache.mahout.cf.taste.impl.recommender.*;
//import org.apache.mahout.cf.taste.impl.similarity.*;
//import org.apache.mahout.cf.taste.model.*;
//import org.apache.mahout.cf.taste.neighborhood.*;
//import org.apache.mahout.cf.taste.recommender.*;
//import org.apache.mahout.cf.taste.similarity.*;
//import java.io.*;
//import java.util.*;
//
//class RecommenderIntro {
//
//  private RecommenderIntro() {
//  }
//
//  public static void main(String[] args) throws Exception {
//
//    DataModel model = new FileDataModel(new File("D:/workspace_greenlight/greenlight_s/Itemrocommender/src/intro.csv"));
//
//    UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
//    UserNeighborhood neighborhood =
//      new NearestNUserNeighborhood(2, similarity, model);
//
//    Recommender recommender = new GenericUserBasedRecommender(
//        model, neighborhood, similarity);
//
//    List<RecommendedItem> recommendations =
//        recommender.recommend(1, 2); //1,2에서 2숫자가 몇개 추천해줄건지
//
//    for (RecommendedItem recommendation : recommendations) {
//      System.out.println(recommendation);
//    }
//
//  }
//
//}
