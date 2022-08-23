package com.sg.alma55.aricles

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import com.sg.alma55.R
import com.sg.alma55.databinding.ActivityArticlesDetailsBinding
import com.sg.alma55.utilities.Constants

class ArticlesDetails : AppCompatActivity() {
    lateinit var binding: ActivityArticlesDetailsBinding
    private var articlesIndex = 0
    private var fileName = ""
    private var backGroundColor = ""
    private var textColor = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticlesDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        articlesIndex = intent.getIntExtra(Constants.ARTICLES_DETALES_INDEX, 0)

        when (articlesIndex) {
            1 -> {
                fileName = "myText1"
                backGroundColor="#0A174E"
                textColor="#F5D042"
            }
            2 -> {
                fileName = "myText2"
                backGroundColor="#2BAE66"
                textColor="#ffffff"
            }
            3 -> {
                fileName = "myText3"
                backGroundColor="#0A174E"
                textColor="#F5D042"
            }
            4 -> {
                fileName = "myText4"
                backGroundColor="#333D79"
                textColor="#ffffff"
            }


            else -> {
                "myText1"
                backGroundColor="#0A174E"
                textColor="#F5D042"
            }
        }
        binding.mainBackground.setBackgroundColor(Color.parseColor(backGroundColor))
        binding.tvArticle.setTextColor(Color.parseColor(textColor))
        activateText()
    }

    private fun activateText() {
        binding.tvArticle.text = application.assets.open(fileName).bufferedReader().use {
            it.readText()
        }
    }
}


/* private fun getText1() {
     val st = "התורה" + "\n" +
             "-------------------------" + "\n" +
             "נמצא בעריכה ...    " + "\n" +
             " ------------------------- " + "\n" +
             ""
     binding.tvArticle2.text = st
 }
 private fun activateText1() {
     binding.tvArticle2.text=application.assets.open("myText1").bufferedReader().use {
         it.readText()
     }
 }
 private fun activateText2() {
     binding.tvArticle2.text=application.assets.open("myText2").bufferedReader().use {
         it.readText()
     }
 }
 private fun activateText3() {
     binding.tvArticle2.text=application.assets.open("myText3").bufferedReader().use {
         it.readText()
     }
 }
 private fun activateText4() {
     binding.tvArticle2.text=application.assets.open("myText4").bufferedReader().use {
         it.readText()
     }
 }
private fun toto(st:String): String{
    return Html.fromHtml("&ldquo;$st&rdquo;").toString()
}
 private fun tot(): String{
     return Html.fromHtml("&ldquo;").toString()
 }
 private fun tot1(): String{
     return  "ק"+tot()+"ג"
 }
 private fun tot2(): String{
     return  "יק"+tot()+"מ"
 }
 private fun tot3(num:Int): String{
     return " $num "+tot2()
 }

 private fun getText2() {
     val st ="יחידת קסם מודעות"+ "\n" +
             "-------------------------" + "\n" +
             "אבטיח מודדים ב:"+tot1()+","+ "\n" +
            toto( "אחי תן לי אבטיח של 10 "+tot1())+ "\n" +
             "מודעות מודדים ב:  "+tot2()+","+ "\n" +
             toto( "אחי אני מרגיש די טמבל כרגע יש לי רק 10  "+tot2())+ "."+"\n" +
             "האדם מקבל בממוצע בכל יום כמות מדודה של 50 "+tot2()+","+ "\n" +
             "יש ימים שהוא מקבל קצת יותר והוא מרגיש   " +toto("חכם")+","+"\n" +
             "ויש ימים שהוא מקבל פחות והוא מרגיש די  "+toto("טמבל")+"."+ "\n" +
             "בעזרת קסם המודעות האדם מפעיל את מערכות המודע שבו. "+ "\n" +
             "זה כמו דלק שמוזרם למערכת המודעות ומאפשר לה לבצע את כל התיפקודים היומיומיים . "+ "\n" +
             "נעשה תחקיר קוסמי מקיף על כל האנושות, ואנחנו מביאים כאן "+ "\n" +
             "את דף הסיכום שהוגש לכוחות עליון. "+ "\n" +
             "ולתחקיר הזה קוראים: "+ "\n" +
            toto( " על מה מוציא האדם הטיפוסי על כדור הארץ את יחידות קסם המודעות שלו ")+ "\n" +
             "-------------"+ "\n" +
             tot3(5)+" - מודעות גוף     "+ "\n" +
             "מודעות לתחושות למחשבות ולרגשות שעוברים באדם ."+ "\n" +
             "-------------"+ "\n" +
             tot3(5)+" - הישרדות בסיסית   "+ "\n" +
             "מודעות לשמירת הגוף מנזקים בסביבה השרדותית ."+ "\n" +
             "-------------"+ "\n" +
             tot3(10)+" - תנאי חיים   "+ "\n" +
             "מודעות להטבת תנאי החיים כגון: פרנסה, קניות , והתנהלות בשגרה."+ "\n" +
             "-------------"+ "\n" +
             tot3(10)+" - הבלים  "+ "\n" +
             "מודעות שאינה הכרחית לקיום הבסיסי ואינה גורמת להתפתחות האדם כגון:"+ "\n" +
                          "סידורים לא הכרחיים,טלווזיה ,פלאפון, פוליטיקה,  שיחות הי-בי, מחשבות סרק."+ "\n" +
             "-------------"+ "\n" +
             tot3(1)+" - מודעות עליונה "+ "\n" +
             "מודעות על החיים כתפיסה."+ "\n" +
             "-------------"+ "\n" +
             "בסוף היום האדם מחזיר בממוצע"+ tot3(14) + "  שלא השתמש בהם. "+  "\n" +
             "-------------"+ "\n" +
             "ככלל מעבר לתחקיר המעניין הזה"+ "\n" +
             "אין כאן אמירה של טוב ורע אלא ניתוח סטטיסטי של אופי האדם."+ "\n" +
             "יש מצבים בהם האפסנאי הקוסמי שמחלק את יחידות התודעה"+ "\n" +
             "רואה שאדם מסוים מחזיר בסוף היום כמות גדולה של יחידות שכאלה."+ "\n" +
             "ולפיכך כדי לחסוך באנרגיה קוסמית"+ "\n" +
             "הוא מספק לו עם השנים כמות התחלתית נמוכה יותר של יחידות קסם מודעות."+ "\n" +

             " ------------------------- " + "\n" +
             ""
            binding.tvArticle2.text = st
 }
*/




