import {
  UserOutlined,
  VideoCameraOutlined,
  UploadOutlined,
  MenuUnfoldOutlined,
  MenuFoldOutlined,
  MailOutlined,SmileOutlined,LoadingOutlined,SolutionOutlined
} from '@ant-design/icons-vue';
const arr =  [
  UserOutlined,
  VideoCameraOutlined,
  UploadOutlined,
  MenuUnfoldOutlined,
  MenuFoldOutlined,
  MailOutlined,SmileOutlined,LoadingOutlined,SolutionOutlined
]
const map = {}
for (let item of arr) {
  map[item.displayName] = item
}
export default map



