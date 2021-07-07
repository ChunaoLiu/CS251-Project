import java.util.Arrays;
import java.util.Random;

public class HashTableChaining {

    ScoreList[] table;
    int capacity;

    /**
     * Constructor: Initializes the table with empty linked lists at each index.
     * @param N - size of the hash table. set capacity = N
     */
    public HashTableChaining(int N) {
        //TODO: Implement Constructor
        table = new ScoreList[N];
        capacity = N;
    }

    /**
     * Adds a <key, value> pair to the hash table. Creates a new ScoreList
     * instance and inserts it into the appropriate list.
     * @param key - the key to be inserted
     * @param value - the value to be inserted
     * @return true if key was inserted otherwise false
     */
    public boolean insert(String key, int value) {
        //TODO: Implement insert
        ScoreList temp = new ScoreList(key, value);
        temp.next = null;
        int index = hash(key);
        if (index > capacity) {
            return false;
        }
        if (table[index] != null) {
            if (table[index].userId.equals(key)) {
                return false;
            }
            if (table[index].next != null) {
                ScoreList temp2 = table[index].next;
                if (temp2.userId.equals(key)) {
                    return false;
                } else {
                    while (temp2.next != null) {
                        if (temp2.userId.equals(key)) {
                            return false;
                        } else {
                            temp2 = temp2.next;
                        }
                    }
                    if (temp2.userId.equals(key)) {
                        return false;
                    } else {
                        temp2.next = temp;
                        return true;
                    }
                }
            } else {
                table[index].next = temp;
                return true;
            }
        } else {
            table[index] = temp;
            return true;
        }
    }

    /**
     * Get the value associated with a particular key
     * @param key - the key to find
     * @return the value associated or null if not found
     */
    public Integer get(String key) {
        //TODO: Implement get
        int index = hash(key);
        if (table[index] == null) {
            return null;
        }
        if (table[index].next != null) {
            ScoreList temp = table[index];
            while (temp != null) {
                if (temp.userId.equals(key)) {
                    return temp.score;
                } else {
                    temp = temp.next;
                }
            }
        } else {
            if (table[index].userId.equals(key)) {
                return table[index].score;
            }
        }
        return null;
    }

    /**
     * removes the hash table entry associated with the key
     * @param key - the key to remove
     * @return true if key was removed otherwise false
     */
    public boolean remove(String key) {
        //TODO: Implement remove
        int index = hash(key);
        if (table[index] == null) {
            return false;
        }
        if (table[index].userId.equals(key)) {
            if (table[index].next != null) {
                table[index] = table[index].next;
                return true;
            } else {
                table[index] = null;
                return true;
            }
        } else {
            if (table[index].next != null) {
                ScoreList temp = table[index];
                while (temp.next != null) {
                    if (temp.next.userId.equals(key)) {
                        temp.next = temp.next.next;
                        return true;
                    } else {
                        temp = temp.next;
                    }
                }
                if (temp.userId.equals(key)) {
                    temp = null;
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    /**
     * Tells whether the key is in the table
     * @param key - the key to look for
     * @return true if key is found otherwise false
     */
    public boolean contains(String key) {
        int index = hash(key);
        if (table[index] != null) {
            if (table[index].userId.equals(key)) {
                return true;
            } else if (table[index].next != null) {
                ScoreList temp = table[index];
                while (temp != null) {
                    if (temp.userId.equals(key)) {
                        return true;
                    } else {
                        temp = temp.next;
                    }
                }
                return table[index].userId.equals(key);
            } else {
                return false;
            }
        } else {
            return false;
        }
        //TODO: Implement contains
//delete this
    }

    /**
     * Gives the number of keys in the table
     * @return the total elements in the table
     */
    public int size() {
        //TODO: Implement size
        int counter = 0;
        for (ScoreList i : table) {
            if (i != null) {
                counter += 1;
                if (i.next != null) {
                    ScoreList temp = i.next;
                    while (temp != null) {
                        counter += 1;
                        temp = temp.next;
                    }
                }
            }
        }
        return counter; //delete this
    }

    /**
     * Returns the hash value of the key. Uses the Java built-in hashcode function.
     * Since the hashcode can be negative, uses 0x7fffffff as a mask to make it positive.
     * ie. ("userid".hashcode() & 0x7fffffff) % capacity.
     * @param key - the key to hash
     * @return the hash value
     */
    public int hash(String key) {
        //TODO: Implement hash
        if (key.hashCode() < 0) {
            return (key.hashCode() & 0x7fffffff) % capacity;
        } else {
            return key.hashCode() % capacity;
        }
    }

    /**
     * ScoreList class
     * An instance of the class is used as an entry in the hashtable
     * DO NOT MODIFY the toString() function
     */
    public static class ScoreList {
        public String userId; //key
        public Integer score; //value
        public ScoreList next;

        public ScoreList(String userId, int score) {
            this.userId = userId;
            this.score = score;
            this.next = null;
        }

        @Override
        public String toString() {
            return String.format("(%s, %d, %s)", userId, score, next);
        }
    }
}
