/*
 * A CardHistory can keep track of the originator’s history 
 * by storing a stack of mementos. 
 * When the originator has to travel back in history, 
 * the caretaker fetches the topmost memento from the stack and passes
 * it to the originator’s restoration method.
 */
package uk.ac.tees.cis2001.pocketbeasts.MementoPattern;

/**
 *
 * @author Yash Patel
 */
public class CardHistory {

    private CardMemento backup;

    /**
     * Backups the CardMemento
     *
     * @param beastCardState A CardMemento representing state to be backed up
     */
    public void makeBackup(CardMemento beastCardState) {
        this.backup = beastCardState;
    }

    /**
     * Gets the backed up CardMemento
     *
     * @return A CardMemeto which will be returned
     */
    public CardMemento undo() {
        return this.backup;
    }
}
