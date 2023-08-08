package exceptions;

public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(String string) {
        super(string);
    }

    public PostNotFoundException(){
        super("this post might not exist, missing post.");
    }
}

