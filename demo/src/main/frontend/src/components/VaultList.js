import { useState } from "react";
import VaultItem from "./VaultItem";

const VaultList = ({ totalVaultList }) => {
  return (
    <div className="VaultList">
      {totalVaultList.map((it) => (
        <VaultItem key={it.missionId} {...it} />
      ))}
    </div>
  );
};

export default VaultList;
