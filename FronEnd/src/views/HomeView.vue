<template>
  <main>
    <div class="container">
      <h1>다함께 SHOW SHOW SHOW!</h1>
      <h3>당신은 풀수있습니다 퀴즈를...</h3>
      <!-- <router-link to="/kakao" class="nav-link">KAKAO</router-link> | -->
      | <router-link to="/rank" class="nav-link">RANK</router-link> |
      <router-link to="/arena" class="nav-link">ARENA</router-link> |
    </div>

    <div class="thumbnails-container">
      <router-link to="/game" class="thumbnail-link">
        <div class="thumbnail">
          <img src="@/assets/tmp/tmp1.jpg" alt="Thumbnail 1">
          <h2 class="start-heading">START</h2>
        </div>
      </router-link>
    </div>
  </main>
</template>

<style scoped>
.container {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 20px;
  background-color: #272727;
  color: #fff;
}

.thumbnails-container {
  margin-top: 20px;
  text-align: center; /* Center the content */
}

h1 {
  font-size: 24px;
  margin: 0;
}

h2 {
  font-size: 20px;
  margin: 10px 0 0; /* Add some margin above the heading */
}

.nav-link {
  text-decoration: none;
  color: #fff;
  margin-right: 10px;
  transition: color 0.3s ease;
}

.nav-link:hover {
  color: #007bff;
}

.thumbnail {
  display: inline-block; /* Make the thumbnail container occupy only the space required by its content */
  background-color: aquamarine;
  margin-right: 10px;
  cursor: pointer;
}

.thumbnail img {
  width: 200px; /* Set the width of the image */
  height: auto; /* Maintain aspect ratio */
}

.start-heading {
  margin-top: 5px; /* Adjust the margin of the title */
}

.thumbnail-link {
  text-decoration: none;
}

.thumbnail:hover {
  background-color: whitesmoke;
}
</style>
