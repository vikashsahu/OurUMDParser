
public class Professor {

	private String name;
	private int numReviews;
	private double score;
	private String rawLinkURL;
	private double GPA;
	
	public Professor () {
		this.name="";
		this.numReviews=0;
		this.score=0.0;
		this.rawLinkURL="";
		this.GPA = 0.0;
	}
	
	public Professor(String name, int numReviews, double score, String rawLinkURL) {
		this.name = name;
		this.numReviews = numReviews;
		this.score = score;
		this.rawLinkURL=rawLinkURL;
	}
	
	public void reset() {
		this.name="";
		this.numReviews=0;
		this.score=0.0;
		this.rawLinkURL="";
		this.GPA = 0.0;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getNumReviews() {
		return numReviews;
	}
	
	public void setNumReviews(int numReviews) {
		this.numReviews = numReviews;
	}
	
	public double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}
	
	public String getRawLinkURL() {
		return rawLinkURL;
	}
	
	public void setRawLinkURL(String rawLinkURL) {
		this.rawLinkURL = rawLinkURL;
	}
	
	public double getGPA() {
		return GPA;
	}
	
	public void setGPA(double GPA) {
		this.GPA = GPA;
	}
}
