package notes;

public class Notes {
    public String note;
    public long notecount;
    protected Notes(String note, long notecount)
    {
        this.setNote(note);
        this.setNotecount(notecount);
    }
    public String getNote()
    {
        return note;
    }
    public void setNote(String note)
    {
        this.note = note;
    }
    public void setNotecount(long notecount)
    {
        this.notecount = notecount;
    }
    public long getNotecount()
    {
        return notecount;
    }
}
