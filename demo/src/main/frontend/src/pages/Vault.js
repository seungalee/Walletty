import { useEffect, useState } from "react";
import MyHeader from "../components/MyHeader";
import VaultList from "../components/VaultList";
import { useNavigate } from "react-router-dom";
import MissionList from "../components/MissionList";

const Vault = () => {
  const navigate = useNavigate();
  const [missionList, setMissionList] = useState([]);
  const [vaultList, setVaultList] = useState([]);
  const [data, setData] = useState([]);
  useEffect(() => {
    if (localStorage.getItem("isLoggedIn") === "true") {
      // fetch("/safe", {
      //   method: "POST",
      //   body: JSON.stringify({
      //     memberId: id,
      //   }),
      //   headers: {
      //     "Content-Type": "application/json",
      //   },
      // })
      //   .then((result) => result.json())
      //   .then((result) => {
      //     setVaultList(result)
      //   })
      //   .catch((err) => {
      //     console.log(err);
      //   });
      // fetch("/chat-gpt/mission", {
      //   method: "POST",
      //   body: JSON.stringify({
      //     memberId: id,
      //   }),
      //   headers: {
      //     "Content-Type": "application/json",
      //   },
      // })
      //   .then((result) => result.json())
      //   .then((result) => {
      //     setMissionList(result);
      //   })
      //   .catch((err) => {
      //     console.log(err);
      //   });
    } else {
      navigate("/login");
    }
  }, []);
  // useEffect(()=>{
  //   const map = new Map();
  //   MissionList.forEach((item) => map.set(item.missionId, item))
  //   VaultList.forEach((item) =>
  //   map.set(item.missionId, { ...map.get(item.missionId), ...item }))
  //   const missionVaultList = Array.from(map.values())
  //   const newList = missionVaultList.map(
  //     ({
  //       missionEntry,
  //       startDate,
  //       endDate,
  //       missionEntry,
  //       now,
  //       ...rest
  //     }) => rest
  //   )
  //   setData(newList)
  // }, [vaultList, missionList])

  const dummydata = [
    {
      missionId: 1,
      missionSen: "택시비를 3만원 절약해라",
      missionMoney: 30000,
      accDeposit: "3333-333-3333",
      inSafe: 1,
    },
    {
      missionId: 2,
      missionSen: "간식비를 2만원 절약해라",
      missionMoney: 20000,
      accDeposit: "3333-334-4444",
      inSafe: null,
    },
  ];
  return (
    <div>
      <MyHeader nowpage={"vault"} />
      <VaultList totalVaultList={dummydata} />
    </div>
  );
};

export default Vault;
