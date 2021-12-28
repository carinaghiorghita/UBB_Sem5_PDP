using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;

namespace Lab4.Model
{
    class StateObject
    {
        public Socket socket = null;
        public const int BUFFER_SIZE = 512;
        public byte[] receiveBuffer = new byte[BUFFER_SIZE];
        public StringBuilder responseContent = new StringBuilder();
        public int clientID;
        public string hostname;
        public string endpointPath;
        public IPEndPoint remoteEndpoint;
        public ManualResetEvent connectDone = new ManualResetEvent(false);
        public ManualResetEvent sendDone = new ManualResetEvent(false);
        public ManualResetEvent receiveDone = new ManualResetEvent(false);
    }
}
