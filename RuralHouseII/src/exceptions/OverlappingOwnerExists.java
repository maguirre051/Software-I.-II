package exceptions;

public class OverlappingOwnerExists extends Exception {
	 private static final long serialVersionUID = 1L;
	 
	 public OverlappingOwnerExists()
	  {
	    super();
	  }
	  
	  public OverlappingOwnerExists(String s)
	  {
	    super(s);
	  }
	}
