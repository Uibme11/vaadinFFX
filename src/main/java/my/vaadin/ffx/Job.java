package my.vaadin.ffx;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Job implements Serializable, Cloneable {

private Long id;
	
	private String email = "";
	
	private String jobName = "";
	
	private String file = "";

	private String command = "";
	
	String aminoAcidPosition = "";
	
	 String chain = "A";
	
	private String aminoAcidChange = "";

	
	public Job() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * Get the value of email
	 *
	 * @return the value of email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set the value of email
	 *
	 * @param email
	 *            new value of email
	 */
	public void setEmail(String email) {
		this.email = email;
		System.out.println(email);
	}

	/**
	 * Get the value of file
	 *
	 * @return the value of file
	 */
	public String getFile() {
		return file;
	}

	/**
	 * Set the value of file
	 *
	 * @param command
	 *            new value of command
	 */
	public void setFile(String file) {
		this.file = file;
		System.out.println(file);
	}
	
	/**
	 * Get the value of command
	 *
	 * @return the value of command
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * Set the value of command
	 *
	 * @param command
	 *            new value of command
	 */
	public void setCommand(String command) {
		this.command = command;
		System.out.println(command);
	}

	/**
	 * Get the value of jobName
	 *
	 * @return the value of jobName
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * Set the value of jobName
	 *
	 * @param jobName
	 *            new value of jobName
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
		System.out.println(jobName);
	}

	/**
	 * Get the value of aminoAcidPosition
	 *
	 * @return the value of aminoAcidPosition
	 */
	public String getAminoAcidPosition() {
		return aminoAcidPosition;
	}

	/**
	 * Set the value of aminoAcidPosition
	 *
	 * @param aminoAcidPosition
	 *            new value of aminoAcidPosition
	 */
	public void setAminoAcidPosition(String aminoAcidPosition) {
		this.aminoAcidPosition = aminoAcidPosition;
		System.out.println(aminoAcidPosition);
	}

	/**
	 * Get the value of chainID
	 *
	 * @return the value of chainID
	 */
	public String getChain() {
		return chain;
	}

	/**
	 * Set the value of chain
	 *
	 * @param chainID
	 *            new value of chain
	 */
	public void setChain(String chain) {
		this.chain = chain;
		System.out.println(chain);
	}
	
	/**
	 * Get the value of aminoAcidChange
	 *
	 * @return the value of aminoAcidChange
	 */
	public String getAminoAcidChange() {
		return aminoAcidChange;
	}

	/**
	 * Set the value of aminoAcidChange
	 *
	 * @param aminoAcidChange
	 *            new value of aminoAcidChange
	 */
	public void setAminoAcidChange(String aminoAcidChange) {
		this.aminoAcidChange = aminoAcidChange;
		System.out.println(aminoAcidChange);
	}
	
	public boolean isPersisted() {
		return id != null;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (this.id == null) {
			return false;
		}

		if (obj instanceof Job && obj.getClass().equals(getClass())) {
			return this.id.equals(((Job) obj).id);
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 43 * hash + (id == null ? 0 : id.hashCode());
		return hash;
	}

	@Override
	public Job clone() throws CloneNotSupportedException {
		return (Job) super.clone();
	}

	/*public static JobRequestService getInstance() {
		// TODO Auto-generated method stub
		return null;
	}*/
}
