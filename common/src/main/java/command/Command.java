package command;

import java.io.Serializable;

public enum Command implements Serializable {
 LOGIN,
 //---
 STUFF_LIST,
 ADD_STUFF,
 UPDATE_STUFF,
 DELETE_STUFF,
 //---
 CREATE_THEME,
 CREATE_QUESTIONS,
 CREATE_ANSWERS,
 CREATE_TEST,
 CREATE_RESULT,
 //--------
 GET_THEME,
 GET_QUESTIONS_BY_QUESTIONS,
 GET_ALL_THEMES,
 GET_ANSWERS,
 GET_THEME_QUESTIONS,
}
