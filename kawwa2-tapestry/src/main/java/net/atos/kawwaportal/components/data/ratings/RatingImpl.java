package net.atos.kawwaportal.components.data.ratings;

import java.util.ArrayList;
import java.util.Date;


public class RatingImpl implements Rating {
	
	private String login;
	
	private String title;
	
	private String comment;
	
	private ArrayList<RatingCriteria> criterias;
	
	private Date date;

	/* (non-Javadoc)
	 * @see net.atos.kawwaportal.components.data.FrontRating#getLogin()
	 */
	public String getLogin() {
		return login;
	}

	/* (non-Javadoc)
	 * @see net.atos.kawwaportal.components.data.FrontRating#setLogin(java.lang.String)
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/* (non-Javadoc)
	 * @see net.atos.kawwaportal.components.data.FrontRating#getTitle()
	 */
	public String getTitle() {
		return title;
	}

	/* (non-Javadoc)
	 * @see net.atos.kawwaportal.components.data.FrontRating#setTitle(java.lang.String)
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/* (non-Javadoc)
	 * @see net.atos.kawwaportal.components.data.FrontRating#getComment()
	 */
	public String getComment() {
		return comment;
	}

	/* (non-Javadoc)
	 * @see net.atos.kawwaportal.components.data.FrontRating#setComment(java.lang.String)
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/* (non-Javadoc)
	 * @see net.atos.kawwaportal.components.data.FrontRating#getCriterias()
	 */
	public ArrayList<RatingCriteria> getCriterias() {
		return criterias;
	}

	/* (non-Javadoc)
	 * @see net.atos.kawwaportal.components.data.FrontRating#setCriterias(java.util.ArrayList)
	 */
	public void setCriterias(ArrayList<RatingCriteria> criterias) {
		this.criterias = criterias;
	}

	/* (non-Javadoc)
	 * @see net.atos.kawwaportal.components.data.FrontRating#getDate()
	 */
	public Date getDate() {
		return date;
	}

	/* (non-Javadoc)
	 * @see net.atos.kawwaportal.components.data.FrontRating#setDate(java.util.Date)
	 */
	public void setDate(Date date) {
		this.date = date;
	}
}
