package com.example.demo.dto;

import com.example.demo.entity.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//lombok dependency추가
@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberDTO { //회원 정보를 필드로 정의
    private Long id;
    private String memberId;
    private String memberPassword;
    private String memberName;
    private String memberAccount;
    private String memberGender;
    private Integer memberAge;

    //lombok 어노테이션으로 getter,setter,생성자,toString 메서드 생략 가능

    public static MemberDTO toMemberDTO(MemberEntity memberEntity){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberId(memberEntity.getMemberId());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberAccount(memberEntity.getMemberAccount());
        memberDTO.setMemberGender(memberEntity.getMemberGender());
        memberDTO.setMemberAge(memberEntity.getMemberAge());
        return memberDTO;
    }
}
//MemberDTO.class