package Control;

public interface IControl {

	/**
	 * convert the file.
	 * 
	 * @param fileName the file name
	 * @return return true, if the convert succeeded,
	 *         otherwise, return false
	 */
	boolean convert(String fileName);
}
