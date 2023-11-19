import { useEffect, useState } from "react";
import MissionList from "../components/MissionList";
import MyHeader from "../components/MyHeader";
import { useNavigate } from "react-router-dom";

const Mission = () => {
  const navigate = useNavigate();
  const id = localStorage.getItem("memberId");
  const [data, setData] = useState([]);
  const [allMissionList, setAllMissionList] = useState([]);

  const dummyData = [
    {
      id: 1,
      startdate: "2023-09-01",
      enddate: "2023-10-01",
      missionEntry: "간식비",
      missionMoney: "20000",
      missionSen: "간식비를 2만원 절약해봐요!!",
      now: "false",
      feedbackSen: "간식비를 지나치게 많이 썼어용",
      accDeposit: "DONE",
      successed: "true",
    },
  ];
  useEffect(() => {
    if (localStorage.getItem("isLoggedIn") === "true") {
      fetch("/chat-gpt/mission", {
        method: "POST",
        body: JSON.stringify({
          memberId: id,
        }),
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then((result) => result.json())
        .then((result) => {
          setAllMissionList(result);
          console.log("mission");
          console.log(result);
        })
        .catch((err) => {
          console.log(err);
        });
      console.log(allMissionList);
    } else {
      navigate("/login");
    }
  }, []);

  useEffect(() => {
    const newList = allMissionList.map(
      ({ missionEntry, missionMoney, now, ...rest }) => rest
    );
    const newnewList = newList.filter((it) => it.accept);
    setData(newnewList);
    console.log(data);
  }, [allMissionList]);

  return (
    <div className="Mission">
      <MyHeader nowpage={"mission"} />
      {data[0] && <MissionList missionList={data} />}
    </div>
  );
};

export default Mission;
