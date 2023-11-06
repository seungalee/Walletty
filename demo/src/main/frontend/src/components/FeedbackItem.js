import { useNavigate } from "react-router-dom";
import styled from "styled-components";

const DateStyled = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 10px 0px;
  color: var(--mygray);
  & > div {
    height: 1px;
    background-color: var(--mygray);
    width: 38%;
  }
`;

const FeedbackStyled = styled.div`
  position: relative;
  max-width: 400px;
  display: inline-block;
  padding: 10px 15px;
  margin: 10px 10px;
  background-color: var(--lgmidpurple);
  &::before {
    position: absolute;
    display: block;
    top: 0;
    content: "◀";
    font-size: 1.3rem;
    left: -18px;
    color: var(--lgmidpurple);
  }
`;
const AcceptStyled = styled.div`
  flex-direction: column;
  margin: 10px 10px;
  padding: 4px 2px;
  display: flex;
  width: 200px;
  height: 60px;
  background-color: var(--lgtpurple);
  border: none;
  font-family: "Noto Sans KR", sans-serif;
  justify-content: center;
  border-radius: 7px;
  & > div {
    display: flex;
    flex-direction: row;
    justify-content: center;
  }
`;

const AcceptButtonStyled = styled.button`
  width: 60px;
  height: 25px;
  margin: 5px 5px;
  border: 1px solid var(--mygray);
  font-family: "Noto Sans KR", sans-serif;
  justify-content: center;
  border-radius: 7px;
  background-color: white;
  &:hover {
    background-color: var(--lgmidpurple);
  }
`;

const FeedbackItem = ({
  missionId,
  feedbackId,
  startdate,
  missionSen,
  sendFront,
  accept,
}) => {
  const navigate = useNavigate();
  const acceptHandler = () => {
    fetch(`missionAccept/${missionId}`, {
      method: "POST",
      body: JSON.stringify({
        accept: 1,
      }),
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((result) => result.json())
      .then((result) => {
        console.log(result);
      })
      .catch((err) => {
        console.log(err);
      });
    navigate("/vault");
  };

  const rejectHandler = () => {};
  return (
    <div className="FeedbackItem">
      <DateStyled>
        <div className="date_line"></div>
        {startdate}
        <div className="date_line"></div>
      </DateStyled>
      <div className="feedback_content">
        <FeedbackStyled>{sendFront}</FeedbackStyled>
      </div>
      <div className="feedback_content">
        <FeedbackStyled>{missionSen}</FeedbackStyled>
      </div>
      <div className="feedback_accept">
        {!accept && (
          <AcceptStyled>
            <div>{"미션을 수락하시겠습니까?"}</div>
            <div>
              <AcceptButtonStyled onClick={acceptHandler}>
                수락
              </AcceptButtonStyled>
              <AcceptButtonStyled onClick={rejectHandler}>
                거절
              </AcceptButtonStyled>
            </div>
          </AcceptStyled>
        )}
      </div>
    </div>
  );
};

export default FeedbackItem;
