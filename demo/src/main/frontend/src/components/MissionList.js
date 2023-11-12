import { useState } from "react";
import MissionItem from "./MissionItem";

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

  const getFilteredMissionList = () => {
    const filterCallBack = (item) => {
      if (statusType === "success") {
        return item.successed === "true";
      } else if (statusType === "fail") {
        return item.successed === "false";
      } else if (statusType === "now") {
        return item.successed === "doing";
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
    </div>
  );
};

export default MissionList;
