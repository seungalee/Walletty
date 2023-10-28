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
  }y
`;

const FeedbackItem = ({ id, startdate, missionSen, feedbackSen }) => {
  return (
    <div className="FeedbackItem">
      <DateStyled>
        <div className="date_line"></div>
        {startdate}
        <div className="date_line"></div>
      </DateStyled>
      <div className="feedback_content">
        <FeedbackStyled>{feedbackSen}</FeedbackStyled>
      </div>
      <div className="feedback_content">
        <FeedbackStyled>{missionSen}</FeedbackStyled>
      </div>
    </div>
  );
};

export default FeedbackItem;
