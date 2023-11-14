import { useState } from "react";
import MissionItem from "./MissionItem";
import styled from "styled-components";
import { useNavigate } from "react-router-dom";

const DemoButtonStyled = styled.button`
  margin-left: 330px;
  margin-top: 350px;
  width: 100px;
  height: 50px;
  background-color: var(--lgmidpurple);
  border-radius: 5px;
  border: 0px;
  border-style: solid;
  margin-bottom: 15px;
  font-weight: 600;
  font-family: "Noto Sans KR", sans-serif;
  &:hover {
    background-color: var(--lmidpurple);
  }
`;

const SelectMenu = ({ value, onChange, optionList }) => {
  return (
    <select
      className="SelectMenu"
      value={value}
      onChange={(e) => onChange(e.target.value)}
    >
      {optionList.map((it, idx) => (
        <option key={it.id} value={it.value}>
          {it.name}
        </option>
      ))}
    </select>
  );
};

const filterStatusList = [
  { value: "all", name: "모두 보기" },
  { value: "success", name: "성공한 미션" },
  { value: "fail", name: "실패한 미션" },
  { value: "now", name: "진행 중인 미션" },
];
const MissionList = ({ missionList }) => {
  const [statusType, setStatusType] = useState("all");
  const id = localStorage.getItem("memberId");
  const navigate = useNavigate();

  const onMissionNextWeek = () => {
    localStorage.setItem("memberId", "bb");
    navigate("/");
  };

  const getFilteredMissionList = () => {
    const filterCallBack = (item) => {
      if (statusType === "success") {
        return item.success === "success";
      } else if (statusType === "fail") {
        return item.success === "fail";
      } else if (statusType === "now") {
        return !item.success;
      }
    };
    const copyList = JSON.parse(JSON.stringify(missionList));
    const filteredMissionList =
      statusType === "all"
        ? copyList
        : copyList.filter((it) => filterCallBack(it));
    return filteredMissionList;
  };
  return (
    <div className="MissionList">
      <div className="mission_select_box">
        <SelectMenu
          value={statusType}
          onChange={setStatusType}
          optionList={filterStatusList}
        />
      </div>
      {getFilteredMissionList().map((it) => (
        <MissionItem key={it.idx} {...it} />
      ))}
      {id === "aa" && (
        <DemoButtonStyled onClick={onMissionNextWeek}>다음주</DemoButtonStyled>
      )}
    </div>
  );
};

export default MissionList;
