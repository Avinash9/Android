package Resources;


import java.util.ArrayList;

public class Ratings {

    public String title;
    public String image;
    public String rating;
    public String releaseYear;
    public ArrayList<String> genre;

    public Ratings(String title,String image,String rating,String releaseYear,ArrayList<String> genre)
    {
        this.title=title;
        this.image=image;
        this.rating=rating;
        this.releaseYear=releaseYear;
        this.genre=genre;

    }

}
