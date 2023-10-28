import { useNavigate } from "react-router-dom";
import styled from "styled-components";

const LeftStyled = styled.div`
  display: flex;
  width: 50%;
  height: 100%;
  justify-content: left;
  align-items: center;
  margin-left: 10px;
  img {
    width: 40px;
    margin-right: 10px;
  }
  div {
    font-size: 22px;
  }
`;

const RightStyled = styled.div`
  display: flex;
  width: 400px;
  height: 100%;
  justify-content: right;
  align-items: center;
  div {
    display: flex;
    width: 20%;
    height: 100%;
    justify-content: center;
    align-items: center;
    //border: 1px solid;
    cursor: pointer;
    &:hover {
      font-weight: 600;
    }
    &:first-child {
      font-weight: ${(props) => props.nowpage === "profile" && "700"};
    }
    &:nth-child(2) {
      font-weight: ${(props) => props.nowpage === "statistics" && "700"};
    }
    &:nth-child(3) {
      font-weight: ${(props) => props.nowpage === "mission" && "700"};
    }
    &:nth-child(4) {
      font-weight: ${(props) => props.nowpage === "feedback" && "700"};
    }
    &:nth-child(5) {
      font-weight: ${(props) => props.nowpage === "safe" && "700"};
    }
  }
`;

const MyHeader = ({ nowpage }) => {
  const navigate = useNavigate();

  const profileClickHandler = (e) => {
    e.preventDefault();
    navigate("/");
  };
  const statisticsClickHandler = (e) => {
    e.preventDefault();
    navigate("/");
  };
  const missionClickHandler = (e) => {
    e.preventDefault();
    navigate("/mission");
  };
  const feedbackClickHandler = (e) => {
    e.preventDefault();
    navigate("/feedback");
  };
  const safeClickHandler = (e) => {
    e.preventDefault();
    navigate("/");
  };
  const logoutClickHandler = (e) => {
    e.preventDefault();
    navigate("/");
  };
  return (
    <div className="MyHeader">
      <LeftStyled>
        <img className="logo" src="/assets/logo.png" />
        <div className="service_name">Service</div>
      </LeftStyled>
      <RightStyled nowpage={`${nowpage}`}>
        <div onClick={profileClickHandler}>프로필</div>
        <div onClick={statisticsClickHandler}>통계</div>
        <div onClick={missionClickHandler}>미션</div>
        <div onClick={feedbackClickHandler}>피드백</div>
        <div onClick={safeClickHandler}>금고</div>
        <div onClick={logoutClickHandler}>로그아웃</div>
      </RightStyled>
    </div>
  );
};

export default MyHeader;