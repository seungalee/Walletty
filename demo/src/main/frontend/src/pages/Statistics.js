import { useNavigate } from "react-router-dom";
import MyHeader from "../components/MyHeader";
import { useEffect, useState } from "react";
import StatisticsGraph from "../components/StatisticsGraph";

const Statistics = () => {
  const navigate = useNavigate();
  const [lastWeekItems, setLastWeekItems] = useState([]);
  const [dataLastWeekItems, setDataLastWeekItems] = useState([]);
  const [thisWeekItems, setThisWeekItems] = useState([]);
  const [dataThisWeekItems, setDataThisWeekItems] = useState([]);
  const [nowDate, setNowDate] = useState();
  const id = localStorage.getItem("memberId");
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
          console.log(result);
          const nowMission = result.filter((it) => it.now === "true");
          console.log(nowMission);
          setNowDate(Number(nowMission[0].startDate) - 1);
        })
        .catch((err) => {
          console.log(err);
        });
    } else {
      navigate("/login");
    }
  }, []);
  useEffect(() => {
    fetch("/statisticsDTOList", {
      method: "POST",
      body: JSON.stringify({
        memberId: id,
        week: nowDate - 7,
      }),
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((result) => result.json())
      .then((result) => {
        console.log(result);
        setLastWeekItems(result);
      })
      .catch((err) => {
        console.log(err);
      });
    fetch("/statisticsDTOList", {
      method: "POST",
      body: JSON.stringify({
        memberId: id,
        week: nowDate,
      }),
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((result) => result.json())
      .then((result) => {
        console.log(result);
        setThisWeekItems(result);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [nowDate]);

  useEffect(() => {
    const sortList = lastWeekItems.sort((a, b) =>
      a.entry.localeCompare(b.entry)
    );
    const newList = sortList.map((it) => {
      if (it.entry === "eatout") {
        return { ...it, name: "외식비" };
      }
      if (it.entry === "cafe") {
        return { ...it, name: "카페비" };
      }
      if (it.entry === "deliver") {
        return { ...it, name: "배달비" };
      }
      if (it.entry === "taxi") {
        return { ...it, name: "택시비" };
      }
      if (it.entry === "snack") {
        return { ...it, name: "간식비" };
      }
      if (it.entry === "beauty") {
        return { ...it, name: "미용비" };
      }
      if (it.entry === "shopping") {
        return { ...it, name: "쇼핑비" };
      }
    });
    setDataLastWeekItems(newList);
  }, [lastWeekItems]);
  useEffect(() => {
    const sortList = thisWeekItems.sort((a, b) =>
      a.entry.localeCompare(b.entry)
    );
    const newList = sortList.map((it) => {
      if (it.entry === "eatout") {
        return { ...it, name: "외식비" };
      }
      if (it.entry === "cafe") {
        return { ...it, name: "카페비" };
      }
      if (it.entry === "deliver") {
        return { ...it, name: "배달비" };
      }
      if (it.entry === "taxi") {
        return { ...it, name: "택시비" };
      }
      if (it.entry === "snack") {
        return { ...it, name: "간식비" };
      }
      if (it.entry === "beauty") {
        return { ...it, name: "미용비" };
      }
      if (it.entry === "shopping") {
        return { ...it, name: "쇼핑비" };
      }
    });
    setDataThisWeekItems(newList);
  }, [thisWeekItems]);

  return (
    <div>
      <MyHeader nowpage={"statistics"} />
      <div className="statistics_page">
        <div className="statistics_graphs">
          <div className="statistics_graph">
            <div className="statistics_title">지난 주 나의 소비 통계 💸</div>
            {dataLastWeekItems[0] && (
              <StatisticsGraph dataval={dataLastWeekItems} />
            )}
          </div>
          <div className="statistics_graph">
            <div className="statistics_title">이번 주 나의 소비 통계 💸</div>
            {dataThisWeekItems[0] && (
              <StatisticsGraph dataval={dataThisWeekItems} />
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Statistics;
