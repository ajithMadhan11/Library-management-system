
interface LibraryInfo {

    abstract Student isValidStudents(int s_id);

    abstract Book isValidBook(int b_id);

    abstract void addStudentToLibrary(String name, String password);

    abstract void addBooktoLibrary(String name);

    abstract void removeStudentFromLibrary(int s_id);

}
