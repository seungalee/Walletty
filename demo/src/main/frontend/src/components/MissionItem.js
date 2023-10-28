import styled from "styled-components";

const MissionStyled = styled.div`
  width: 500px;
  height: 70px;
  padding: 10px 15px 15px 10px;
  margin: 15px 15px;
  box-shadow: 1px 1px 3px var(--mygray);
  background-color: ${(props) => {
    if (props.successed === "true") return "rgba(187, 208, 255, 0.3)";
    else if (props.successed === "false") return "rgba(255, 214, 255, 0.3)";
    else return "rgba(201, 193, 255, 0.3)";
  }};
`;

const MissionStatusStyled = styled.div`
  display: flex;
  width: 60px;
  height: 20px;
  font-size: 13px;
  border: 1px solid;
  border-radius: 10px;
  align-items: center;
  justify-content: center;
  margin-bottom: 4px;
`;

const MissionItem = ({ id, startdate, enddate, missionSen, successed }) => {
  return (
    <div className="MissionItem">
      <MissionStyled successed={`${successed}`}>
        <MissionStatusStyled>
          {successed === "true" && "성공"}
          {successed === "false" && "실패"}
          {successed === "doing" && "진행 중"}
        </MissionStatusStyled>
        <div className="mission_date">{`${startdate} ~ ${enddate}`}</div>
        <div className="mission_content">{missionSen}</div>
      </MissionStyled>
    </div>
  );
};

export default MissionItem;
