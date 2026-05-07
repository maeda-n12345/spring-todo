package jp.co.sfrontier.todo.enums;

/**
 *  todo のステータスを表す Enum
 */
public enum TodoStatusEnum {
	
	INCOMPLETE(0,"未完了"),
	
	COMPLETE(1,"完了");
	
	/**
	 *  DB 保存用の値
	 */
	private final Integer value;
	
	/**
	 * 画面表示用ラベル
	 */
	private final String label;

	private TodoStatusEnum(Integer value, String label) {
		this.value = value;
		this.label = label;
	}

	public Integer getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
	
}
