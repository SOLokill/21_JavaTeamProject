package vo;

import java.io.Serializable;

import dto.Member;

public class MessageVO implements Serializable {
	private static final long serialVersionUID = 1234567890L;
	private Member member;
	private String text;

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
