import styled from "styled-components";
import MyHeader from "../components/MyHeader";
import { useUserState } from "../context/UserContext";
import { useEffect, useState } from "react";
import Swal from "sweetalert2";
import { useNavigate } from "react-router-dom";

const HomeImgStyled = styled.div`
  display: flex;
  & > img {
    height: 320px;
    @media (max-width: 768px) {
      height: 150px;
    }
  }
  @media (max-width: 768px) {
    margin-top: 20px;
    width: 70vw;
    justify-content: space-between;
  }
`;

const HomeTextStyled = styled.div`
  width: 500px;
  font-size: 21px;
  margin-right: 50px;
  @media (max-width: 768px) {
    width: 90vw;
    margin-left: 10vw;
  }
  & > div {
    &:first-child {
      color: #6247aa;
      font-size: 60px;
      font-family: "Binggrae-Two";
      @media (max-width: 768px) {
        font-size: 40px;
      }
    }
    &:nth-child(2) {
      color: #6247aa;
      font-size: 25px;
      margin-bottom: 50px;
      font-weight: 600;
      @media (max-width: 768px) {
        font-size: 20px;
      }
    }
    &:nth-child(3) {
      width: 470px;
      margin-bottom: 15px;
      @media (max-width: 768px) {
        width: 80vw;
        font-size: 18px;
      }
    }
    &:nth-child(4) {
      @media (max-width: 768px) {
        width: 80vw;
        font-size: 18px;
      }
    }
  }
`;

const Home = () => {
  const [missionList, setMissionList] = useState([]);
  const navigate = useNavigate();
  const id = localStorage.getItem("memberId");
  useEffect(() => {
    if (localStorage.getItem("isLoggedIn") === "true") {
      fetch("/chat-gpt/mission", {
        method: "POST",
        body: JSON.stringify({
          memberId: id,
        }),
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then((result) => result.json())
        .then((result) => {
          setMissionList(result);
        })
        .catch((err) => {
          console.log(err);
        });
    }
  }, []);

  useEffect(() => {
    const filteredMissionList = missionList.filter(
      (it) => it.now === "true" && !it.accept
    );
    if (filteredMissionList[0]) {
      Swal.fire({
        html: `<div class="feedback_alert_box"><img src="/assets/chatbot_mom_head.png" /><div>아직 확인하지 않은 피드백이 있습니다! 피드백 페이지로 이동합니다.</div></div>`,
        customClass: "feedback-alert",
        showConfirmButton: false,
        position: "top",
        timer: 1500,
      }).then(function (result) {
        setTimeout(() => {
          navigate("/feedback");
        }, 500);
      });
    }
  }, [missionList]);
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
