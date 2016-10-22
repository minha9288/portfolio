//package Recommand.mahout;
//
//import java.io.File;
//import java.util.List;
//
//import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
//import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
//import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
//import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
//import org.apache.mahout.cf.taste.impl.similarity.SpearmanCorrelationSimilarity;
//import org.apache.mahout.cf.taste.model.DataModel;
//import org.apache.mahout.cf.taste.model.JDBCDataModel;
//import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
//import org.apache.mahout.cf.taste.recommender.RecommendedItem;
//import org.apache.mahout.cf.taste.recommender.Recommender;
//import org.apache.mahout.cf.taste.similarity.UserSimilarity;
//
//import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
//
//public class UserRecommend1 {
//	public static void main(String[] args) throws Exception {
//
//		// DataModel model = new FileDataModel(new File("data/movies.csv"));
//
//		MysqlDataSource dataSource = new MysqlDataSource();
//		dataSource.setServerName("localhost");
//		dataSource.setUser("root");
//		dataSource.setPassword("1111");
//		dataSource.setDatabaseName("greenlight");
//
//		// MySQLJDBCDataModel(DataSource dataSource, String preferenceTable,
//		// String userIDColumn, String ite3mIDColumn, String preferenceColumn,
//		// String timestampColumn)
//		
//		//datebase
//		JDBCDataModel dataModel = new MySQLJDBCDataModel(dataSource,
//				"my_prefs_table", "my_user_column", "my_item_column",
//				"my_pref_value_column", "my_timestamp_column");
//		// 사용자,아이템id,선호값
//		DataModel model = dataModel;
//
//		UserSimilarity similarity = new SpearmanCorrelationSimilarity(model);
//
//		UserNeighborhood neighborhood =
//
//		new NearestNUserNeighborhood(2, similarity, model);
//
//
//		Recommender recommender = new GenericUserBasedRecommender(
//
//		model, neighborhood, similarity);
//
//		List<RecommendedItem> recommendations =
//
//		recommender.recommend(1, 3); // 사용자 1에게 음식 3개 추천
//
//		for (RecommendedItem recommendation : recommendations) {
//
//			System.out.println(recommendation);
//
//		}
//
//	}
//}
