package br.com.exemplo.LiterAlura.model;

public enum Languages {

	EN("en","ingles"),
	PT("pt","portugues"),
	FR("fr","frances"),
	DE("de","alemao"),
	RU("ru","russo"),
	ES("es","espanhol");
	
	private String acronLanguage;
	private String ptLanguage;
	
	Languages(String acronLanguage, String ptLanguage) {
		this.acronLanguage = acronLanguage;
		this.ptLanguage = ptLanguage;
	}
	
	public static String getAcronLanguage(Languages languages) {
		return languages.acronLanguage;
	}
	
	public static String getPtLanguage(Languages languages) {
		return languages.ptLanguage;
	}
}
