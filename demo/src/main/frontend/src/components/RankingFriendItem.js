import styled from "styled-components";

const RankFriendItemStyled = styled.div`
  display: flex;
  flex-direction: column;
  background-color: ${(props) => {
    if (props.ismyid === "true") return "#eae9fe";
    else return "white";
  }};
  align-items: center;
  padding: 10px 10px;
  border-radius: 15px;
  box-shadow: 1px 1px 2px #cccccc;
`;

const RankFriendItemImgStyled = styled.div`
  & img {
    height: 150px;
  }
`;
const RankFriendItemInfoStyled = styled.div`
  font-size: 16px;
  & div:first-child {
    font-size: 18px;
    margin-bottom: 2px;
  }
`;
const RankingFriendItem = ({
  isFriend,
  isMyId,
  level,
  memberId,
  position,
  ranks,
  totalSavingMoney,
  index,
}) => {
  return (
    <div className="RankingFriendItem">
      <RankFriendItemStyled ismyid={`${isMyId}`}>
        <RankFriendItemImgStyled>
          <img src={`/assets/character_${position}.png`} />
        </RankFriendItemImgStyled>
        <RankFriendItemInfoStyled>
          <div>현재 {index + 1}위</div>
          <div>{memberId} 님</div>
          <div>
            레벨 {level}, <b>{position}</b>
          </div>
          <div>이번 주 {totalSavingMoney}원 절약 중!</div>
        </RankFriendItemInfoStyled>
      </RankFriendItemStyled>
    </div>
  );
};

export default RankingFriendItem;
