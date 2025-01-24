package notes;

public class Notes implements Cloneable//create the class Notes and implements teh cloneable to use the clone
{
    public String note;// variable to store the note type
    public long notecount;// variable to store the note count
    protected Notes(String note, long notecount)// constructor to assign the values to the note and note count
    {
        this.setNote(note);
        this.setNotecount(notecount);
    }
    public String getNote()//method to return the note
    {
        return note;
    }
    public void setNote(String note)// method to set the value of note to the variable note
    {
        this.note = note;
    }
    public void setNotecount(long notecount)//method to set the value to the variable note count
    {
        this.notecount = notecount;
    }
    public long getNotecount()//method to return the note count
    {
        return notecount;
    }
    public Notes clone() throws CloneNotSupportedException// method to clone the note
    {
        return (Notes) super.clone();// Perform a shallow copy using the Object class's clone method.
    }
}
