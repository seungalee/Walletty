import Button from "../components/Button";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const Survey_goalmoney = () => {
  const navigate = useNavigate();
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
        surveyId: id,
        fixedEntry: fixedList.map((row) => row.value).toString(),
        goalEntry1: goalList[0].value,
        goalMoney1: Number(goalmoneyList.goalmoney1),
        goalEntry2: goalList[1].value,
        goalMoney2: Number(goalmoneyList.goalmoney2),
        goalEntry3: goalList[2].value,
        goalMoney3: Number(goalmoneyList.goalmoney3),
      }),
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        alert("목표 설정이 완료되었습니다!");
        navigate("/");
      })
      .catch((error) => console.log(error));
  };
  return (
    <div>
      <div className="survey_qs">
        <div className="survey_q_box">
          <div className="survey_q_text">
            절약하고 싶은 항목별로 목표 금액을 설정해 주세요.<br></br> 목표
            금액은 해당 항목 소비량의 5% 이상이어야 합니다.
          </div>
          <div className="goalmoney_list_box">
            {goalList.map((item, index) => (
              <div
                key={`goalmoney_${item.value}`}
                className="goalmoney_box_element"
              >
                {`${item.name} 항목의 절약 목표 금액을 설정해 주세요:`}
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
