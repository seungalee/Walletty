import styled from "styled-components";

const MissionStyled = styled.div`
  font-size: 19px;
  width: 700px;
  height: auto;
  padding: 10px 15px 15px 10px;
  margin: 15px 15px;
  box-shadow: 1px 2px 3px #cccccc;
  background-color: ${(props) => {
    if (props.success === "success") return "rgba(187, 208, 255, 0.3)";
    else if (props.success === "fail") return "rgba(255, 214, 255, 0.3)";
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

const MissionItem = ({
  missionId,
  startDate,
  endDate,
  missionSen,
  success,
}) => {
  return (
    <div className="MissionItem">
      <MissionStyled success={`${success}`}>
        <MissionStatusStyled>
          {success === "success" && "성공"}
          {success === "fail" && "실패"}
          {!success && "진행 중"}
        </MissionStatusStyled>
        <div className="mission_date">{`${startDate} ~ ${endDate}`}</div>
        <div className="mission_content">{missionSen}</div>
      </MissionStyled>
    </div>
  );
};

export default MissionItem;
