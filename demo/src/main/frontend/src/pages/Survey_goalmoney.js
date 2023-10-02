import Button from "../components/Button";
import { useState } from "react";

const Survey_goalmoney = () => {
  const goalList = JSON.parse(localStorage.getItem("goal_entry"));
  const [goalMoneyList, setGoalMoneyList] = useState();
  const onSubmitHanlder = (e) => {
    e.preventDefault();
    localStorage.setItem("surveyDone", true);
  };
  return (
    <div>
      <div className="survey_qs">
        <div className="survey_q_box">
          <div className="survey_q_text">
            절약하고 싶은 항목별로 목표 금액을 설정해 주세요. 목표 금액은 해당
            항목 소비량의 5% 이상이어야 합니다.
          </div>
          <div className="goalmoney_list_box">
            {goalList.map((item) => (
              <div key={`goalmoney_${item.value}`}>
                {item.name} 목표 금액을 설정해 주세요:
                <input name="money" type="text" />
              </div>
            ))}
          </div>
        </div>
        <Button text={"설정 완료"} onClick={onSubmitHanlder} type={"survey"} />
      </div>
    </div>
  );
};

export default Survey_goalmoney;
