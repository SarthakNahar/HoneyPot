import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

class IntTextDocument extends PlainDocument

{
    public void insertString(int o,String s,AttributeSet a )
    
    {
        try{
            if(s==null) return;
            
            String valid="0123456789";
            if(valid.indexOf(s)==-1)
                return;
            else
                super.insertString(o,s,a);
            
        }
        catch(BadLocationException b){
        }
        
    }
    
    
}
