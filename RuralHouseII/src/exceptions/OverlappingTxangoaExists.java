package exceptions;

public class OverlappingTxangoaExists extends Exception {
	private static final long serialVersionUID = 1L;
	 
	 public OverlappingTxangoaExists()
	  {
	    super();
	  }
	  
	  public OverlappingTxangoaExists(String s)
	  {
	    super(s);
	  }

}
