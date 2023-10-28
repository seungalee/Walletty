import { useState } from "react";
import FeedbackItem from "./FeedbackItem";
import styled from "styled-components";

const ButtonStyled = styled.button`
  width: 100px;
  height: 30px;
  border: none;
  font-family: "Noto Sans KR", sans-serif;
  justify-content: center;
  border-radius: 7px;
  &:hover {
    background-color: var(--lgtpurple);
  }
`;

const FeedbackList = ({ feedbackList, listLen }) => {
  const [feedbackNum, setFeedbackNum] = useState(2);
  const getMoreFeedbackList = () => {
    return feedbackList.slice(listLen - feedbackNum);
  };
  const seeMoreHandler = () => {
    if (feedbackNum + 2 <= listLen) {
      setFeedbackNum(feedbackNum + 2);
    } else {
      setFeedbackNum(listLen);
    }
  };
  return (
    <div className="FeedbackList">
      <div className="feedback_button">
        {feedbackNum !== listLen && (
          <ButtonStyled onClick={seeMoreHandler}>더보기</ButtonStyled>
        )}
      </div>
      {getMoreFeedbackList().map((it) => (
        <FeedbackItem key={it.idx} {...it} />
      ))}
    </div>
  );
};

export default FeedbackList;
