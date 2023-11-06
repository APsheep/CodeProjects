package kindergarten;
/**
 * This class represents a Classroom, with:
 * - an SNode instance variable for students in line,
 * - an SNode instance variable for musical chairs, pointing to the last student in the list,
 * - a boolean array for seating availability (eg. can a student sit in a given seat), and
 * - a Student array parallel to seatingAvailability to show students filed into seats 
 * --- (more formally, seatingAvailability[i][j] also refers to the same seat in studentsSitting[i][j])
 * 
 * @author Ethan Chou
 * @author Kal Pandit
 * @author Maksims Kurjanovics Kravcenko
 */
public class Classroom {
    private SNode studentsInLine;             // when students are in line: references the FIRST student in the LL
    private SNode musicalChairs;              // when students are in musical chairs: references the LAST student in the CLL
    private boolean[][] seatingAvailability;  // represents the classroom seats that are available to students
    private Student[][] studentsSitting;      // when students are sitting in the classroom: contains the students

    /**
     * Constructor for classrooms. Do not edit.
     * @param l passes in students in line
     * @param m passes in musical chairs
     * @param a passes in availability
     * @param s passes in students sitting
     */
    public Classroom ( SNode l, SNode m, boolean[][] a, Student[][] s ) {
		studentsInLine      = l;
        musicalChairs       = m;
		seatingAvailability = a;
        studentsSitting     = s;
	}
    /**
     * Default constructor starts an empty classroom. Do not edit.
     */
    public Classroom() {
        this(null, null, null, null);
    }

    /**
     * This method simulates students coming into the classroom and standing in line.
     * 
     * Reads students from input file and inserts these students in alphabetical 
     * order to studentsInLine singly linked list.
     * 
     * Input file has:
     * 1) one line containing an integer representing the number of students in the file, say x
     * 2) x lines containing one student per line. Each line has the following student 
     * information separated by spaces: FirstName LastName Height
     * 
     * @param filename the student information input file
     */
    public void makeClassroom ( String filename ) {
        StdIn.setFile(filename);
        int n = StdIn.readInt();
        Student[] names = new Student[n];
        for(int i = 0; i<n; i++){
            String FN = StdIn.readString(); 
            String LN = StdIn.readString();
            int H = StdIn.readInt();
            names[i] = new Student(FN, LN, H);
        }
        for(int i = 0; i < n; i++){
            for(int j = i+1; j< names.length; j++){
                if(names[i].compareNameTo(names[j]) > 0){
                    Student temp = names[i];
                    names[i] = names [j];
                    names[j] = temp;
                }
            }
        }
        studentsInLine = new SNode();
        SNode students = studentsInLine;
        for(int i = 0; i < names.length; i++){
            students.setStudent(names[i]);
            if(i < names.length - 1) students.setNext(new SNode());
            students = students.getNext();
        }
        // WRITE YOUR CODE HERE
    }

    /**
     * 
     * This method creates and initializes the seatingAvailability (2D array) of 
     * available seats inside the classroom. Imagine that unavailable seats are broken and cannot be used.
     * 
     * Reads seating chart input file with the format:
     * An integer representing the number of rows in the classroom, say r
     * An integer representing the number of columns in the classroom, say c
     * Number of r lines, each containing c true or false values (true denotes an available seat)
     *  
     * This method also creates the studentsSitting array with the same number of
     * rows and columns as the seatingAvailability array
     * 
     * This method does not seat students on the seats.
     * 
     * @param seatingChart the seating chart input file
     */
    public void setupSeats(String seatingChart) {
        StdIn.setFile(seatingChart);
        int r = StdIn.readInt();
        int c = StdIn.readInt();
        seatingAvailability = new boolean[r][c];
        studentsSitting = new Student[r][c];
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
            seatingAvailability[i][j] =StdIn.readBoolean();
            }
        }
	// WRITE YOUR CODE HERE
    }

    /**
     * 
     * This method simulates students taking their seats in the classroom.
     * 
     * 1. seats any remaining students from the musicalChairs starting from the front of the list
     * 2. starting from the front of the studentsInLine singly linked list
     * 3. removes one student at a time from the list and inserts them into studentsSitting according to
     *    seatingAvailability
     * 
     * studentsInLine will then be empty
     */
    public void seatStudents () {
        for(int i = 0; i< seatingAvailability.length; i++){
            for(int j = 0; j<seatingAvailability[0].length; j++){
                if(seatingAvailability[i][j]){
                    if(musicalChairs != null){
                        studentsSitting[i][j] = musicalChairs.getStudent();
                    }
                    if(studentsInLine != null){
                        studentsSitting[i][j] = studentsInLine.getStudent();
                        if(studentsInLine.getNext() != null){
                            //studentsSitting[i][j+1] = studentsInLine.getStudent();
                            studentsInLine = studentsInLine.getNext();
                        }
                        else{
                            studentsInLine = null;
                        }
                    }
                }
            }
        }
	// WRITE YOUR CODE HERE
	
    }

    /**
     * Traverses studentsSitting row-wise (starting at row 0) removing a seated
     * student and adding that student to the end of the musicalChairs list.
     * 
     * row-wise: starts at index [0][0] traverses the entire first row and then moves
     * into second row.
     */
    public void insertMusicalChairs () {

        for (int i=0; i < seatingAvailability.length; i++) { 
            for (int j=0; j<seatingAvailability[0].length; j++)
            {
                if (studentsSitting[i][j] != null)
                {
                    SNode cute = new SNode();
                    cute.setStudent(studentsSitting[i][j]);
                     if (musicalChairs == null)
                     {
                        musicalChairs = cute;
                        musicalChairs.setNext(cute);
                     }
                     else
                     {
                        SNode now = musicalChairs.getNext();
                        SNode head =musicalChairs.getNext();
                        while (now != musicalChairs)
                        {
                            now = now.getNext();
                        }
                        
                        now.setNext(cute);
                        cute.setNext(head);
                        musicalChairs = cute;
                     }
                     studentsSitting[i][j] = null;
                }
            }
        }

     }
     private Student remMusicalChairWin()
     {
        Student winStudent = null;
        if (musicalChairs.getNext() == musicalChairs)
           {
             winStudent = musicalChairs.getStudent();
             musicalChairs = null;
           }
           return winStudent;
     }
     private Student remFromMusicalChairWin(int x)
     {
        SNode now = musicalChairs.getNext();
        SNode pre = musicalChairs;
        if (x == 0)
        {
            SNode temp = now.getNext();
            musicalChairs.setNext(temp);
            return now.getStudent();
        }


        for (int i = 0; i < x; i++)
        {
            pre = now;
            now = now.getNext();
        }
        if(now == musicalChairs)
        {   
            pre.setNext(musicalChairs.getNext());
            musicalChairs= pre;
        }
        pre.setNext(now.getNext());
        return now.getStudent();
     }

     private int getStuCountInMusicalChairs()
     {
        SNode now = musicalChairs.getNext();
        int count = 1;
        do
        {
         now = now.getNext();
         count++;
        }while(now != musicalChairs);
        return count;
    }
    
    private void replStuInLine(Student stu)
    {
        SNode removed = new SNode();
        removed.setStudent(stu);
        if (studentsInLine == null)
        {
            studentsInLine = removed;
        }
        else if (studentsInLine.getStudent().getHeight() >= removed.getStudent().getHeight())//new header.
        {
            SNode temp = studentsInLine;
            studentsInLine = removed;
            studentsInLine.setNext(temp);

        }
        else 
        {
            SNode now = studentsInLine;
            SNode pre = null;
            while (now != null)
            {
                if (now.getStudent().getHeight()< removed.getStudent().getHeight())
                {
                    pre = now;
                    now = now.getNext();
                    continue;

                }
                else 
                  break;
            }
            if (pre != null)
            {
                pre.setNext(removed);
                removed.setNext(now);
            }

        }
        return ;

        // WRITE YOUR CODE HERE

     }

    /**
     * 
     * This method repeatedly removes students from the musicalChairs until there is only one
     * student (the winner).
     * 
     * Choose a student to be elimnated from the musicalChairs using StdRandom.uniform(int b),
     * where b is the number of students in the musicalChairs. 0 is the first student in the 
     * list, b-1 is the last.
     * 
     * Removes eliminated student from the list and inserts students back in studentsInLine 
     * in ascending height order (shortest to tallest).
     * 
     * The last line of this method calls the seatStudents() method so that students can be seated.
     */
    public void playMusicalChairs() {
        if (musicalChairs == null)
        {
           insertMusicalChairs();
        }
        int c = getStuCountInMusicalChairs();
        
        while(c > 1){
            
             int removex = StdRandom.uniform(c);
             c--;
             Student stu = remFromMusicalChairWin(removex);
             replStuInLine(stu);
        } 
        Student winner = remMusicalChairWin();
        boolean winSeated = false;
        for (int i=0; i < seatingAvailability.length; i++) { 
         for (int j=0; j<seatingAvailability[0].length; j++)
         {
             if(seatingAvailability[i][j])
             {
                 studentsSitting[i][j] = winner;
                 seatingAvailability[i][j] = false;
                 winSeated = true;
                 break;
             }
             
         }
         if (winSeated) 
               break;
        }  
        seatStudents();
        // WRITE YOUR CODE HERE

    } 

    /**
     * Insert a student to wherever the students are at (ie. whatever activity is not empty)
     * Note: adds to the end of either linked list or the next available empty seat
     * @param firstName the first name
     * @param lastName the last name
     * @param height the height of the student
     */
    public void addLateStudent ( String firstName, String lastName, int height ) {
        SNode late = new SNode();
        Student lateS = new Student();
        lateS.setFirstName( firstName);
        lateS.setLastName(lastName);
        lateS.setHeight(height);
        late.setStudent(lateS);
        if (studentsInLine !=  null) 
        {   
            SNode now = studentsInLine;
            while(now.getNext() != null)
            {
                now = now.getNext();
            }
            now.setNext(late);
        }
        else if(musicalChairs != null){ 
            SNode t = musicalChairs.getNext();
            musicalChairs.setNext(late);
            late.setNext(t);
            musicalChairs = late;

        }
        else {

            for (int i=0; i < seatingAvailability.length; i++) { 
                for (int j=0; j<seatingAvailability[0].length; j++)
                {
                    if(seatingAvailability[i][j] && studentsSitting[i][j] == null)
                    {
                        studentsSitting[i][j] = lateS;
                        i = seatingAvailability.length;
                        break;
                    }
                }
            }
        }
        

        // WRITE YOUR CODE HERE
        
    }

    /**
     * A student decides to leave early
     * This method deletes an early-leaving student from wherever the students 
     * are at (ie. whatever activity is not empty)
     * 
     * Assume the student's name is unique
     * 
     * @param firstName the student's first name
     * @param lastName the student's last name
     */
    public void deleteLeavingStudent ( String firstName, String lastName ) {
        if (studentsInLine !=  null) 
        {
            SNode pre = null;
            SNode now = studentsInLine;
            boolean fresh = false;
            while(now.getNext() != null)
            {
                if (now.getStudent().getFirstName().equals(firstName) && now.getStudent().getLastName().equals(lastName))
                {
                    if (pre == null) 
                    {
                        studentsInLine = studentsInLine.getNext();
                        fresh = true;
                        break;
                    }
                    else{
                
                        pre.setNext(now.getNext());
                        fresh = true;
                        break;
                    }
                }
                pre = now;
                now = now.getNext();
            }
            if (!fresh)
            {
                if (now.getStudent().getFirstName().equals(firstName) && now.getStudent().getLastName().equals(lastName))
                {
                        pre.setNext(now.getNext());
                        fresh = true;
                    
                }

            }
            
        }
        else if(musicalChairs != null){ 
            SNode pre = musicalChairs;
            SNode now = musicalChairs.getNext();
            do
            {
                if (now.getStudent().getFirstName().equals(firstName) && now.getStudent().getLastName().equals(lastName))
                {
                    if(musicalChairs == now) 
                    {
                        musicalChairs = pre;
                        break;
                    }
                    else{
                
                        pre.setNext(now.getNext());
                        break;
                        
                    }
                }
                pre = now;
                now = now.getNext();
            }while (now != musicalChairs.getNext());

        }
        else   {

            for (int i=0; i < seatingAvailability.length; i++) { 
                for (int j=0; j<seatingAvailability[0].length; j++)
                {
                    if(studentsSitting[i][j] != null && studentsSitting[i][j].getFirstName().equals(firstName) && studentsSitting[i][j].getLastName().equals(lastName))
                    {
                        studentsSitting[i][j] = null;
                        i = seatingAvailability.length;
                        break;
                    }
                }
            }
        }
        
        // WRITE YOUR CODE HERE

    }

    /**
     * Used by driver to display students in line
     * DO NOT edit.
     */
    public void printStudentsInLine () {

        //Print studentsInLine
        StdOut.println ( "Students in Line:" );
        if ( studentsInLine == null ) { StdOut.println("EMPTY"); }

        for ( SNode ptr = studentsInLine; ptr != null; ptr = ptr.getNext() ) {
            StdOut.print ( ptr.getStudent().print() );
            if ( ptr.getNext() != null ) { StdOut.print ( " -> " ); }
        }
        StdOut.println();
        StdOut.println();
    }

    /**
     * Prints the seated students; can use this method to debug.
     * DO NOT edit.
     */
    public void printSeatedStudents () {

        StdOut.println("Sitting Students:");

        if ( studentsSitting != null ) {
        
            for ( int i = 0; i < studentsSitting.length; i++ ) {
                for ( int j = 0; j < studentsSitting[i].length; j++ ) {

                    String stringToPrint = "";
                    if ( studentsSitting[i][j] == null ) {

                        if (seatingAvailability[i][j] == false) {stringToPrint = "X";}
                        else { stringToPrint = "EMPTY"; }

                    } else { stringToPrint = studentsSitting[i][j].print();}

                    StdOut.print ( stringToPrint );
                    
                    for ( int o = 0; o < (10 - stringToPrint.length()); o++ ) {
                        StdOut.print (" ");
                    }
                }
                StdOut.println();
            }
        } else {
            StdOut.println("EMPTY");
        }
        StdOut.println();
    }

    /**
     * Prints the musical chairs; can use this method to debug.
     * DO NOT edit.
     */
    public void printMusicalChairs () {
        StdOut.println ( "Students in Musical Chairs:" );

        if ( musicalChairs == null ) {
            StdOut.println("EMPTY");
            StdOut.println();
            return;
        }
        SNode ptr;
        for ( ptr = musicalChairs.getNext(); ptr != musicalChairs; ptr = ptr.getNext() ) {
            StdOut.print(ptr.getStudent().print() + " -> ");
        }
        if ( ptr == musicalChairs) {
            StdOut.print(musicalChairs.getStudent().print() + " - POINTS TO FRONT");
        }
        StdOut.println();
    }

    /**
     * Prints the state of the classroom; can use this method to debug.
     * DO NOT edit.
     */
    public void printClassroom() {
        printStudentsInLine();
        printSeatedStudents();
        printMusicalChairs();
    }

    /**
     * Used to get and set objects.
     * DO NOT edit.
     */

    public SNode getStudentsInLine() { return studentsInLine; }
    public void setStudentsInLine(SNode l) { studentsInLine = l; }

    public SNode getMusicalChairs() { return musicalChairs; }
    public void setMusicalChairs(SNode m) { musicalChairs = m; }

    public boolean[][] getSeatingAvailability() { return seatingAvailability; }
    public void setSeatingAvailability(boolean[][] a) { seatingAvailability = a; }

    public Student[][] getStudentsSitting() { return studentsSitting; }
    public void setStudentsSitting(Student[][] s) { studentsSitting = s; }

}
