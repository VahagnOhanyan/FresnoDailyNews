package org.fresno.adapter.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.38.0)",
    comments = "Source: FresnoDailyNews.proto")
public final class FresnoDailyNewsGrpc {

  private FresnoDailyNewsGrpc() {}

  public static final String SERVICE_NAME = "fresnodailynews.FresnoDailyNews";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ExtractKeywordsRequest,
      ExtractKeywordsResponse> getExtractKeywordMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ExtractKeyword",
      requestType = ExtractKeywordsRequest.class,
      responseType = ExtractKeywordsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ExtractKeywordsRequest,
      ExtractKeywordsResponse> getExtractKeywordMethod() {
    io.grpc.MethodDescriptor<ExtractKeywordsRequest, ExtractKeywordsResponse> getExtractKeywordMethod;
    if ((getExtractKeywordMethod = FresnoDailyNewsGrpc.getExtractKeywordMethod) == null) {
      synchronized (FresnoDailyNewsGrpc.class) {
        if ((getExtractKeywordMethod = FresnoDailyNewsGrpc.getExtractKeywordMethod) == null) {
          FresnoDailyNewsGrpc.getExtractKeywordMethod = getExtractKeywordMethod =
              io.grpc.MethodDescriptor.<ExtractKeywordsRequest, ExtractKeywordsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ExtractKeyword"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ExtractKeywordsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ExtractKeywordsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FresnoDailyNewsMethodDescriptorSupplier("ExtractKeyword"))
              .build();
        }
      }
    }
    return getExtractKeywordMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FresnoDailyNewsStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FresnoDailyNewsStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FresnoDailyNewsStub>() {
        @Override
        public FresnoDailyNewsStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FresnoDailyNewsStub(channel, callOptions);
        }
      };
    return FresnoDailyNewsStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FresnoDailyNewsBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FresnoDailyNewsBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FresnoDailyNewsBlockingStub>() {
        @Override
        public FresnoDailyNewsBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FresnoDailyNewsBlockingStub(channel, callOptions);
        }
      };
    return FresnoDailyNewsBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FresnoDailyNewsFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FresnoDailyNewsFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FresnoDailyNewsFutureStub>() {
        @Override
        public FresnoDailyNewsFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FresnoDailyNewsFutureStub(channel, callOptions);
        }
      };
    return FresnoDailyNewsFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class FresnoDailyNewsImplBase implements io.grpc.BindableService {

    /**
     */
    public void extractKeyword(ExtractKeywordsRequest request,
                               io.grpc.stub.StreamObserver<ExtractKeywordsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getExtractKeywordMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getExtractKeywordMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                ExtractKeywordsRequest,
                ExtractKeywordsResponse>(
                  this, METHODID_EXTRACT_KEYWORD)))
          .build();
    }
  }

  /**
   */
  public static final class FresnoDailyNewsStub extends io.grpc.stub.AbstractAsyncStub<FresnoDailyNewsStub> {
    private FresnoDailyNewsStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected FresnoDailyNewsStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FresnoDailyNewsStub(channel, callOptions);
    }

    /**
     */
    public void extractKeyword(ExtractKeywordsRequest request,
                               io.grpc.stub.StreamObserver<ExtractKeywordsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getExtractKeywordMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class FresnoDailyNewsBlockingStub extends io.grpc.stub.AbstractBlockingStub<FresnoDailyNewsBlockingStub> {
    private FresnoDailyNewsBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected FresnoDailyNewsBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FresnoDailyNewsBlockingStub(channel, callOptions);
    }

    /**
     */
    public ExtractKeywordsResponse extractKeyword(ExtractKeywordsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getExtractKeywordMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class FresnoDailyNewsFutureStub extends io.grpc.stub.AbstractFutureStub<FresnoDailyNewsFutureStub> {
    private FresnoDailyNewsFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected FresnoDailyNewsFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FresnoDailyNewsFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ExtractKeywordsResponse> extractKeyword(
        ExtractKeywordsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getExtractKeywordMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_EXTRACT_KEYWORD = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final FresnoDailyNewsImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(FresnoDailyNewsImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_EXTRACT_KEYWORD:
          serviceImpl.extractKeyword((ExtractKeywordsRequest) request,
              (io.grpc.stub.StreamObserver<ExtractKeywordsResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class FresnoDailyNewsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FresnoDailyNewsBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return FresnoDailyNewsService.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FresnoDailyNews");
    }
  }

  private static final class FresnoDailyNewsFileDescriptorSupplier
      extends FresnoDailyNewsBaseDescriptorSupplier {
    FresnoDailyNewsFileDescriptorSupplier() {}
  }

  private static final class FresnoDailyNewsMethodDescriptorSupplier
      extends FresnoDailyNewsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FresnoDailyNewsMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (FresnoDailyNewsGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FresnoDailyNewsFileDescriptorSupplier())
              .addMethod(getExtractKeywordMethod())
              .build();
        }
      }
    }
    return result;
  }
}
