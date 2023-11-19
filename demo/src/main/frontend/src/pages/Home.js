import styled from "styled-components";
import MyHeader from "../components/MyHeader";
import { useUserState } from "../context/UserContext";

const HomeImgStyled = styled.div`
  display: flex;
  & > img {
    height: 320px;
  }
`;

const HomeTextStyled = styled.div`
  width: 500px;
  font-size: 21px;
  margin-right: 50px;
  & > div {
    &:first-child {
      color: var(--mstrpurple);
      font-size: 60px;
      font-family: "Binggrae-Two";
    }
    &:nth-child(2) {
      color: var(--mstrpurple);
      font-size: 25px;
      margin-bottom: 50px;
    }
    &:nth-child(3) {
      width: 470px;
      margin-bottom: 15px;
    }
  }
`;

const Home = () => {
  const { user } = useUserState();
  return (
    <div>
      <MyHeader />
      <div className="home_all">
        <HomeTextStyled>
          <div>₩alletty</div>
          <div>잔소리 ai 챗봇이 도와주는 소비 습관 개선 서비스</div>
          <div>
            잔소리 ai 챗봇인 <b>MonneyBot</b>에게 소비 습관에 대한{" "}
            <b>사용자 맞춤형 피드백</b>과 목표 달성 <b>미션</b>을 받아보세요!
          </div>
          <div>
            나의 <b>캐릭터</b>를 성장시키며 소비 습관을 개선해 보아요 :)
          </div>
        </HomeTextStyled>
        <HomeImgStyled>
          <img src="/assets/chatbot_mom.png" />
          <img src="/assets/character_org.png" />
        </HomeImgStyled>
      </div>
    </div>
  );
};

export default Home;
