import { defineStore } from "pinia";
import axios from "axios";

const REST_RANK_API = `http://localhost:8080/ansmoon/problem`;

export const useRankStore = defineStore("rank", () => {
  const dataList = ref([]);

  const searchList = async (data) => {
    console.log(data);
    await axios
      .get(`${REST_RANK_API}/search`, {
        params: data,
      })
      .then((response) => {
        dataList.value = response.data;
      });
  };

  return { dataList, searchList };
});

// CSS 추가 및 수정
import { ref } from "vue";

const dataList = ref([]);

// dataList 테이블 스타일 수정
const tableStyle = `
/* rank.js */

/* dataList 테이블 스타일 수정 */
table {
  margin: 0 auto; /* 수평 가운데 정렬 */
  width: 90%; /* 전체 너비의 90% 사용 */
  max-width: 800px; /* 최대 너비 지정 */
  border-collapse: collapse;
}

th, td {
  padding: 12px; /* 셀 안 여백 */
  text-align: left;
  border-bottom: 1px solid #ddd;
}

th {
  background-color: #f2f2f2;
  color: #333;
  font-weight: bold;
}

tr:nth-child(even) {
  background-color: #f9f9f9;
}

tr:hover {
  background-color: #f2f2f2;
}

`;

export { dataList, tableStyle };
