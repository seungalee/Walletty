import MyHeader from "../components/MyHeader";
import VaultList from "../components/VaultList";

const Vault = () => {
  const data = [
    {
      missionId: 1,
      missionSen: "택시비를 3만원 절약해라",
      missionMoney: 30000,
      accDeposit: "3333-333-3333",
      status: "DONE",
    },
    {
      missionId: 2,
      missionSen: "간식비를 2만원 절약해라",
      missionMoney: 20000,
      accDeposit: "3333-334-4444",
      status: "WAITING_FOR_DEPOSIT",
    },
  ];
  return (
    <div>
      <MyHeader nowpage={"vault"} />
      <VaultList vaultList={data} />
    </div>
  );
};

export default Vault;
