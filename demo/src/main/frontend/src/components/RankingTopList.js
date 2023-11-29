import { useState } from "react";
import styled from "styled-components";

const RankingItemStyled = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 370px;
  padding-right: 20px;
  padding-top: 5px;
  margin: 20px 20px;
  border-radius: 20px;
  background-color: white;
  box-shadow: 1px 1px 3px #cccccc;
`;

const RankingItemImgStyled = styled.div`
  & img {
    height: 180px;
  }
`;

const RankingItemInfoStyled = styled.div`
  :first-child {
    font-size: 19px;
    font-weight: 600;
    margin-bottom: 5px;
  }
  font-size: 18px;
`;

const RankingTopList = ({ rankingTopList }) => {
  console.log(rankingTopList);
  return (
    <div className="RankingTopList">
      <div>주간 절약왕 랭킹🏆</div>
      <div className="rank_weekly_box">
        <RankingItemStyled>
          <RankingItemImgStyled>
            <img src={`/assets/character_${rankingTopList[0].position}.png`} />
          </RankingItemImgStyled>
          <RankingItemInfoStyled>
            <div>현재 전체 1위🥇</div>
            <div>{rankingTopList[0].memberId} 님</div>
            <div>
              레벨 {rankingTopList[0].level},{" "}
              <b>{rankingTopList[0].position}</b>
            </div>
            <div>이번 주 {rankingTopList[0].totalSavingMoney}원 절약 중!</div>
          </RankingItemInfoStyled>
        </RankingItemStyled>

        <RankingItemStyled>
          <RankingItemImgStyled>
            <img src={`/assets/character_${rankingTopList[1].position}.png`} />
          </RankingItemImgStyled>
          <RankingItemInfoStyled>
            <div>현재 전체 2위🥈</div>
            <div>{rankingTopList[1].memberId} 님</div>
            <div>
              레벨 {rankingTopList[1].level},{" "}
              <b>{rankingTopList[1].position}</b>
            </div>
            <div>이번 주 {rankingTopList[1].totalSavingMoney}원 절약 중!</div>
          </RankingItemInfoStyled>
        </RankingItemStyled>

        <RankingItemStyled>
          <RankingItemImgStyled>
            <img src={`/assets/character_${rankingTopList[2].position}.png`} />
          </RankingItemImgStyled>
          <RankingItemInfoStyled>
            <div>현재 전체 3위🥉</div>
            <div>{rankingTopList[2].memberId} 님</div>
            <div>
              레벨 {rankingTopList[2].level},{" "}
              <b>{rankingTopList[2].position}</b>
            </div>
            <div>이번 주 {rankingTopList[2].totalSavingMoney}원 절약 중!</div>
          </RankingItemInfoStyled>
        </RankingItemStyled>
      </div>
    </div>
  );
};

export default RankingTopList;
