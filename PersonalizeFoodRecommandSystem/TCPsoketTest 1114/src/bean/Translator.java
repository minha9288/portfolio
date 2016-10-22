package bean;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

import android.os.StrictMode;
import android.util.Log;


public class Translator {
   private String text_after_translate;
   private String text_before_translate;
   
   public String translating(String text_before_translate, String nation) {
      

		Log.e("test",text_before_translate+nation+"");
	   StrictMode.enableDefaults();
      Translate.setClientId("rlatjgml38"); // ID
      Translate.setClientSecret("nOFqHOM+LLYxO9SFbV+8Sp7cplsPf6xYJoJfY6qpdsI="); // Secret
      
      Log.e("sdf",nation);
      try {
         if(nation.equals("KR"))
            text_after_translate = Translate.execute(text_before_translate, Language.KOREAN); 
         else if(nation.equals("CN"))
            text_after_translate = Translate.execute(text_before_translate, Language.CHINESE_SIMPLIFIED); 
         else if(nation.equals("JP"))
            text_after_translate = Translate.execute(text_before_translate, Language.JAPANESE); 
         else if(nation.equals("US") || nation.equals("UK"))
            text_after_translate = Translate.execute(text_before_translate, Language.ENGLISH); 
         else if(nation.equals("FR"))
            text_after_translate = Translate.execute(text_before_translate, Language.FRENCH); 
      } catch (Exception e) {
         e.printStackTrace();
         text_after_translate = e.toString();
      }
      Log.e("input", (text_after_translate==null)+"");
      return text_after_translate;
   }
}

