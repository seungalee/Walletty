import RankingFriendItem from "./RankingFriendItem";

const RankingFriendList = ({ rankingFriendList }) => {
  console.log(rankingFriendList);
  //const id = localStorage.getItem("memberId");
  const id = "bb";

  const sortFriendListByRank = () => {
    return rankingFriendList.sort((a, b) => a.ranks - b.ranks);
  };
  const myIndex = sortFriendListByRank().findIndex((it) => it.memberId === id);

  return (
    <div className="RankingFriendList">
      <div>주간 친구 랭킹🏆</div>
      <div>
        내 등수: {myIndex + 1}/{sortFriendListByRank().length}
      </div>
      <div className="rank_friend_box">
        {sortFriendListByRank().map((it, index) => (
          <RankingFriendItem
            key={it.memberId}
            {...it}
            isMyId={myIndex === index}
            index={index}
          />
        ))}
      </div>
    </div>
  );
};

export default RankingFriendList;
