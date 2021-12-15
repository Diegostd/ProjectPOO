package chatSystem.model;

public class User {

	 private String nom;
	 private String hostaddr;
	 //see if a timer parameter would be useful 
	    protected User(String nom, String addr){
	        this.nom=nom;
	        this.hostaddr=addr;
	    }
	    
	    public String getNom(){ return this.nom;}
	    public void setNom(String name){ nom = name;}
	    public String getAddr(){ return this.hostaddr;}
	    
	    @Override
	    public String toString()
	    {
	        return this.getNom()+" : "+this.getAddr();
	    }
	    
	    @Override
	    public boolean equals(Object o)
	    {
	        User u = null;
	        if (o != null && o instanceof User){
	            u = (User) o;
	        }
	        
	        if(u != null)
	            return ((this.hostaddr.equals( u.getAddr() ) ) );
	        else
	            return false;
	    }
	
}
