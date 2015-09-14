import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Extract {

	public static void main(String[] args) throws IOException {
		Document doc = Jsoup.connect("http://www.ourumd.com/viewreviews/?all").get();
		Element table = doc.select("#content table").first();//so table doesn't have to be 'Elements'
		Elements rows = table.select("tr");

		String profName="";
		String profLink="";
		Double profScore=0.0;
		Integer numReviews=0;
		Double gpa = 0.0;

		Professor p = new Professor();

		File fout = new File("data.txt");
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

		//start at 1 to skip the heading (<tr>)
		for (int i=1; i < rows.size(); i++) {
			String dataString="";

			Element profNameElement= rows.get(i).select("td").get(1).select("a").first();
			profName = profNameElement.text().toString();
			profLink = profNameElement.attr("href");

			Element profScoreElement = rows.get(i).select("td").get(2);
			profScore = Double.parseDouble(profScoreElement.text().toString());

			Element numReviewsElement = rows.get(i).select("td").get(4);
			numReviews = Integer.parseInt(numReviewsElement.text().toString());

			//the gpa is located at another url. this method gets it
			gpa = getGPAFromURL(convertRawURL(profLink));
			System.out.println(i + ": " + gpa);
			if (!gpa.equals(-999.0)) {


				bw.write(profName + ',');
				bw.write(profScore.toString() + ',');
				bw.write(gpa.toString() + ",");
				bw.write(numReviews.toString());
				bw.newLine();

				System.out.println("name: " + profName);
				/*System.out.println();
				System.out.println("score: " + profScore);
				System.out.println("gpa: " + gpa);
				System.out.println("num of reviews: " + numReviews);
				System.out.println("url: " + profLink);
				System.out.println();*/
			}


		}

		bw.close();
		System.out.println("finished");
	}

	public static String convertRawURL(String rawURL) {
		//from "reviews/Wyss-Gallifent, J"
		//to: http://www.ourumd.com/prof/Wyss-Gallifent,%20J

		String s = rawURL.substring(8);
		String [] pieces = s.split(" ");
		String f = pieces[0];
		if (pieces.length>1) {
			f+="%20";
			f+=pieces[1];
		}
		

		return f;
	}

	public static double getGPAFromURL(String profURL) throws IOException {
		Document doc = Jsoup.connect("http://www.ourumd.com/prof/" + profURL).get();
		Element gpaElement = doc.getElementsByClass("coursetitle").first();

		String gpaString = gpaElement.toString();
		//System.out.println(gpaString);
		//System.out.println("substring: <" + gpaString.substring(32,33) + ">");

		//System.out.println(gpaString.substring(32,33).equals("") || gpaString.substring(32,38).trim().equals("<br>"));
		
		if (gpaString.substring(32,33).equals(" ") || gpaString.substring(32,38).trim().equals("<br>")) {
			return -999;
		}

		Double gpa = Double.parseDouble(gpaString.substring(32, 37));
		return gpa;
	}
}