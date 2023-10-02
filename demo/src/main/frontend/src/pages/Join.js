import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useUserDispatch } from "../context/UserContext";
import Button from "../components/Button";

const URL = "http://localhost:8080";

const Join = () => {
  const [state, setState] = useState({
    id: "",
    password: "",
    passwordCheck: "",
    name: "",
    gender: "female",
    age: "",
    account: "",
  });
  const dispatch = useUserDispatch();

  const handleChangeState = (e) => {
    setState({
      ...state,
      [e.target.name]: e.target.value,
    });
  };

  const onSubmitHandler = (e) => {
    e.preventDefault();
    if (
      !state.id ||
      !state.password ||
      !state.passwordCheck ||
      !state.name ||
      !state.age ||
      !state.account
    ) {
      alert("모든 항목을 입력해 주세요.");
    } else {
      dispatch({
        type: "LOGIN",
        userId: state.id,
      });
      fetch("http://localhost:8080/member/join", {
        method: "POST",
        body: JSON.stringify({
          id: state.id,
          pw: state.password,
          name: state.name,
          account: state.account,
          gender: state.gender,
          age: state.age,
        }),
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then((response) => response.json())
        .then((result) => {
          if (result.MESSAGE === "SUCCESS") {
            console.log("회원가입 성공");
            navigate("/survey");
          }
        });
    }
  };

  const genderList = [
    { value: "female", name: "여자" },
    { value: "male", name: "남자" },
  ];
  const navigate = useNavigate();

  return (
    <div className="Join">
      <div className="join_box">
        <div className="join_box_element">
          <div className="join_box_text">아이디</div>
          <input
            name="id"
            type="id"
            value={state.id}
            onChange={handleChangeState}
          />
        </div>
        <div className="join_box_element">
          <div className="join_box_text">비밀번호</div>
          <input
            name="password"
            type="password"
            value={state.password}
            onChange={handleChangeState}
          />
        </div>
        <div className="join_box_element">
          <div className="join_box_text">비밀번호 확인</div>
          <div className="join_box_check">
            <input
              name="passwordCheck"
              type="password"
              value={state.passwordCheck}
              onChange={handleChangeState}
            />
            {/* {state.password !== "" &&
              state.password !== state.passwordCheck && (
                <div className="passwordCheck_wrong">
                  비밀번호가 일치하지 않습니다.
                </div>
              )} */}
          </div>
        </div>
        <div className="join_box_element">
          <div className="join_box_text">이름</div>
          <input
            name="name"
            type="text"
            value={state.name}
            onChange={handleChangeState}
          />
        </div>
        <div className="join_box_element">
          <div className="join_box_text">성별</div>
          <select
            name="gender"
            onChange={handleChangeState}
            value={state.gender}
          >
            {genderList.map((option) => (
              <option key={option.value} value={option.value}>
                {option.name}
              </option>
            ))}
          </select>
        </div>
        <div className="join_box_element">
          <div className="join_box_text">만 나이</div>
          <input
            name="age"
            type="text"
            value={state.age}
            onChange={handleChangeState}
          />
        </div>
        <div className="join_box_element">
          <div className="join_box_text">계좌번호</div>
          <input
            name="account"
            type="text"
            value={state.account}
            onChange={handleChangeState}
          />
        </div>
        <div className="join_box_element">
          <Button text={"가입하기"} onClick={onSubmitHandler} />
        </div>
      </div>
    </div>
  );
};

export default Join;
