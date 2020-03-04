package utility;

import javax.servlet.http.HttpServletRequest;

import model.Survey;
import model.question.MultipleChoiceQuestion;

/**
 * Takes a request object. Updates the stored survey element in the request by:
 * Extracting the survey info elements from the request and stores them in the
 * survey object The Length input this method takes from the request is in
 * Seconds.
 */
public class AddToSurvey {

	public static void add(HttpServletRequest request) {
		// exctracts the survey object
		Survey survey = (Survey) request.getSession().getAttribute("survey");

		// Exstracts the survey info
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String length = request.getParameter("length");
		String repeatable = request.getParameter("repeatable");
		// checks to avoid nullpointer exceptions
		if (repeatable == null) {
			repeatable = "";
		}
		String traversable = request.getParameter("traversable");
		if (traversable == null) {
			traversable = "";
		}

		// parse extracted info to the survey
		survey.setName(name);
		survey.setDescription(description);

		if (!length.equals("")) {
			survey.setLength(survey.convertLength(length));
		}

		// check if repetable
		if (repeatable.equals("repeatable")) {
			survey.setRepeatable(true);
		} else {
			survey.setRepeatable(false);
		}

		// check if traversable
		if (traversable.equals("traversable")) {
			survey.setTraversable(true);
		} else {
			survey.setTraversable(false);
		}

		// Question knows how manny question there are.
		for (int i = 0; i < survey.getQuestions().size(); i++) {
			// Get parameter from request and pares to object
			String text = request.getParameter("text" + i);
			survey.getQuestions().get(i).setText(text);

			// Type logic
			if (survey.getQuestions().get(i).getType().equals("mc")) {
				MultipleChoiceQuestion question = (MultipleChoiceQuestion) survey.getQuestions().get(i);
				String singleAnswer = request.getParameter("singleAnswer");
				
				if (singleAnswer == null) {
					singleAnswer = "";
				}
				if (singleAnswer.equals("singleAnswer")) {
					question.setSingleAnswer(true);
				} else {
					question.setSingleAnswer(false);
				}
				
				for (int j = 0; j < question.getOptions().size(); j++) {
					// Get parameter from request and pares to object
					String option = request.getParameter("option" + j + i);
					question.getOptions().set(j, option);
				}
			}
		}

		request.getSession().setAttribute("survey", survey);
	}

	public static boolean addWithValidation(HttpServletRequest request) {
		boolean validated = true;
		String errorMsg = "Field must be between 0 and 500 characters";
		String errorMsgOption = "Question must have at least two options";
		String errorMsgQuestion = "Survey must have at least one question";
		// exctracts the survey object
		Survey survey = (Survey) request.getSession().getAttribute("survey");

		// Exstracts the survey info
		String name = Validator.makeValidInput(request.getParameter("name"));
		String description = Validator.makeValidInput(request.getParameter("description"));
		String length = request.getParameter("length");
		String repeatable = request.getParameter("repeatable");
		// checks to avoid nullpointer exceptions
		if (repeatable == null) {
			repeatable = "";
		}
		String traversable = request.getParameter("traversable");
		if (traversable == null) {
			traversable = "";
		}

		// parse extracted info to the survey
		if (name != null) {
			request.getSession().removeAttribute("errorName");
			survey.setName(name);
		} else {
			request.getSession().setAttribute("errorName", errorMsg);
			survey.setName(null);
			validated = false;
		}

		if (description != null) {
			request.getSession().removeAttribute("errorDescription");
			survey.setDescription(description);
		} else {
			request.getSession().setAttribute("errorDescription", errorMsg);
			survey.setDescription(null);
			validated = false;
		}

		if (length.equals("")) {
			survey.setLength(0L);
		} else {
			survey.setLength(survey.convertLength(length));
		}

		// check if repetable
		if (repeatable.equals("repeatable")) {
			survey.setRepeatable(true);
		} else {
			survey.setRepeatable(false);
		}

		// check if traversable
		if (traversable.equals("traversable")) {
			survey.setTraversable(true);
		} else {
			survey.setTraversable(false);
		}

		if (survey.getQuestions().size() == 0) {
			request.getSession().setAttribute("errorName", errorMsgQuestion);
			validated = false;
		}
		
		// Question knows how manny question there are.
		for (int i = 0; i < survey.getQuestions().size(); i++) {
			// Get parameter from request and pares to object
			String text = Validator.makeValidInput(request.getParameter("text" + i));

			if (text != null) {
				request.getSession().removeAttribute("errorText" + i);
				survey.getQuestions().get(i).setText(text);
			} else {
				request.getSession().setAttribute("errorText" + i, errorMsg);
				survey.getQuestions().get(i).setText(null);
				validated = false;
			}

			// Type logic
			if (survey.getQuestions().get(i).getType().equals("mc")) {
				MultipleChoiceQuestion question = (MultipleChoiceQuestion) survey.getQuestions().get(i);
				String singleAnswer = request.getParameter("singleAnswer");
				
				if (singleAnswer == null) {
					singleAnswer = "";
				}
				if (singleAnswer.equals("singleAnswer")) {
					question.setSingleAnswer(true);
				} else {
					question.setSingleAnswer(false);
				}
				
				if (question.getOptions().size() == 0 || question.getOptions().size() == 1) {
					request.getSession().setAttribute("errorText" + i, errorMsgOption);
					validated = false;
				}
				
				for (int j = 0; j < question.getOptions().size(); j++) {
					// Get parameter from request and pares to object
					String option = Validator.makeValidInput(request.getParameter("option" + j + i));

					if (option != null) {
						request.getSession().removeAttribute("errorOption" + j + i);
						question.getOptions().set(j, option);
					} else {
						request.getSession().setAttribute("errorOption" + j + i, errorMsg);
						question.getOptions().set(j, null);
						validated = false;
					}
				}
			}
		}

		request.getSession().setAttribute("survey", survey);

		return validated;
	}
}
