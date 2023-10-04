import Button from "../components/Button";
import { useState } from "react";

const Survey_goalmoney = () => {
  const id = localStorage.getItem("memberId");
  const fixedList = JSON.parse(localStorage.getItem("fixed_entry"));
  const goalList = JSON.parse(localStorage.getItem("goal_entry"));
  const [goalmoneyList, setGoalmoneyList] = useState({
    goalmoney1: "",
    goalmoney2: "",
    goalmoney3: "",
  });
  const handleChangeState = (e) => {
    setGoalmoneyList({
      ...goalmoneyList,
      [e.target.name]: e.target.value,
    });
  };
  const onSubmitHandler = (e) => {
    e.preventDefault();
    fetch("/member/survey", {
      method: "POST",
      body: JSON.stringify({
        //local에서 꺼내쓰기
        surveyId: "ididd",
        fixedEntry: fixedList.map((row) => row.name),
        goalEntry1: goalList[0].name,
        goalMoney1: Number(goalmoneyList.goalmoney1),
        goalEntry2: goalList[1].name,
        goalMoney2: Number(goalmoneyList.goalmoney2),
        goalEntry3: goalList[2].name,
        goalMoney3: Number(goalmoneyList.goalmoney3),
      }),
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
      })
      .catch((error) => console.log(error));
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
            {goalList.map((item, index) => (
              <div key={`goalmoney_${item.value}`}>
                {item.name}
                목표 금액을 설정해 주세요:
                <input
                  name={`goalmoney${index + 1}`}
                  type="text"
                  onChange={handleChangeState}
                />
              </div>
            ))}
          </div>
        </div>
        <Button text={"설정 완료"} onClick={onSubmitHandler} type={"survey"} />
      </div>
    </div>
  );
};

export default Survey_goalmoney;
